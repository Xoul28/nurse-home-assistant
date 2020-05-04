package com.dopezebraevm.nursehomeassistant.view.task

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.LocaleResolver
import kotlinx.android.synthetic.main.alert_picker.*
import kotlinx.android.synthetic.main.alert_picker.view.*
import kotlinx.android.synthetic.main.fragment_create_new_task.*


class TaskBuilderFragment(newTaskVO: NewTaskVO? = null) : BaseFragment(R.layout.fragment_create_new_task) {

    private var taskTemplateVO: List<TaskTemplateVO> = emptyList()
    private var searchTaskTemplateVO: MutableList<TaskTemplateVO> = mutableListOf()
    private lateinit var adapter: TaskTemplateRVAdapter
    private var newTask: NewTaskVO = NewTaskVO()

    init {
        newTaskVO?.let {
            newTask = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateFields()

        setupRV()

        et_task_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                newTask.title = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        et_task_description.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                newTask.description = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        ll_period_selected.setOnClickListener {
            showAlert()
        }

        btn_save.setOnClickListener {
            (activity as MainActivity).closeFragment(newTask)
        }

        btn_back.setOnClickListener {
            activity?.onBackPressed()
        }

        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isNotBlank() && searchTaskTemplateVO.size > 0) {
                    adapter.setupTemplates(taskTemplateVO)
                    adapter.notifyDataSetChanged()
                    searchTaskTemplateVO.clear()
                }
                searchTaskTemplateVO.addAll( taskTemplateVO.filter { it.title.contains(p0.toString(),true) } )
                if (searchTaskTemplateVO.size > 0) {
                    adapter.setupTemplates(searchTaskTemplateVO)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    fun showAlert() {
        var dialog: AlertDialog? = null
        val values = listOf(
            "Каждый час",
            "Раз в 2 часа",
            "Раз в 4 часа",
            "Раз в день",
            "Раз в неделю",
            "Утром",
            "Днем",
            "Вечером",
            "Утром и Вечером",
            "Утром, Днем и Вечером"
        )

        var newValue = ""
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Выберите периодичность")

        val customLayout: View =
            layoutInflater.inflate(R.layout.alert_picker, null)
        builder.setView(customLayout)

        builder.setPositiveButton("OK") { _, _ ->
            if (newValue.isNotEmpty())
                tv_period_executing_task.text = newValue
        }

        builder.setNegativeButton("Отмена") { _, _ ->
            dialog?.dismiss()
        }

        dialog = builder.create()
        dialog.show()

        customLayout.number_picker?.setOnValueChangedListener { picker, oldVal, newVal -> newValue = values[newVal - 1]}
        customLayout.number_picker?.minValue = 1
        customLayout.number_picker?.maxValue = values.size
        customLayout.number_picker?.displayedValues = values.toTypedArray()
    }

    fun setupRV() {
        taskTemplateVO = LocaleResolver.getTaskTemplateList().map {
            TaskTemplateVO(
                it.action,
                it.description,
                it.periodicity,
                it.type
            )
        }
        this.adapter = TaskTemplateRVAdapter {
            updateTask(it)
        }

        rv_rask_template.adapter = adapter
        adapter.setupTemplates(taskTemplateVO)
        adapter.notifyDataSetChanged()
    }

    fun updateTask(taskTemplateVO: TaskTemplateVO) {
        newTask.title = taskTemplateVO.title
        newTask.description = taskTemplateVO.description
        newTask.period = taskTemplateVO.period
        newTask.type = taskTemplateVO.type
        updateFields()
    }

    fun updateFields() {
        et_task_title.setText(newTask.title)
        et_task_description.setText(newTask.description)
        if (newTask.period.isNotBlank())
            tv_period_executing_task.text = newTask.period
        else tv_period_executing_task.text = "Выберите периодичность"
    }
}