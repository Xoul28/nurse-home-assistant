package com.dopezebraevm.nursehomeassistant.view.plan

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dopezebraevm.nursehomeassistant.App
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.LocaleResolver
import com.dopezebraevm.nursehomeassistant.data.db.PlanDB
import com.dopezebraevm.nursehomeassistant.view.ArticaleFragment
import com.dopezebraevm.nursehomeassistant.view.MainFragment
import com.dopezebraevm.nursehomeassistant.view.help.ArticalWashingHeadFragment
import com.dopezebraevm.nursehomeassistant.view.task.NewTaskVO
import com.dopezebraevm.nursehomeassistant.view.task.TaskBuilderFragment
import com.dopezebraevm.nursehomeassistant.view.task.TaskTemplateVO
import kotlinx.android.synthetic.main.fragment_create_plan.*
import java.util.*

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
class CreatePlanFragment : BaseFragment(R.layout.fragment_create_plan) {

    companion object {

        const val LOGIN_MODE = 0
        const val DEFAULT_MODE = 1

        const val ARGUMENT_MODE = "argument_mode"

        fun newInstance(mode: Int = DEFAULT_MODE): CreatePlanFragment {
            val fragment = CreatePlanFragment()
            val bundle = Bundle()
            bundle.putInt(ARGUMENT_MODE, mode)
            fragment.arguments = bundle
            return fragment
        }
    }

    var mode = DEFAULT_MODE
    lateinit var adapter: RvManageTaskAdapter
    lateinit var tasks: MutableList<ManageTaskVO>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.showBottomNavigationBar()
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.aqua_haze)
        mode = arguments?.getInt(ARGUMENT_MODE) ?: DEFAULT_MODE

        if (mode == DEFAULT_MODE) setupDefaultMode()
        else setupLoginMode()
        setupRv()

        val plans = App.get(requireContext()).dataBase.getPlanDao().getAll()
        tasks = if (plans?.isNotEmpty() != true) {
            LocaleResolver.getPlanTemplateList().map {
                ManageTaskVO(
                    UUID.randomUUID().toString(),
                    it.action,
                    it.type,
                    it.description,
                    it.periodicity
                )
            }.toMutableList()
        } else {
            plans.map {
                ManageTaskVO(
                    it.planId,
                    it.title,
                    it.type,
                    it.description,
                    it.whenExecute
                )
            }.toMutableList()
        }
        adapter.setTasks(tasks)

        btn_next.setOnClickListener {
            val plans = tasks.map {
                PlanDB(
                    planId = it.id,
                    title = it.title,
                    type = it.type,
                    description = it.description,
                    whenExecute = it.whenExecute
                )
            }
            App.get(requireContext()).dataBase.getPlanDao().updateList(plans)
            App.get(requireContext()).generateTaskInteractor.generate()
            App.get(requireContext()).prefHelper.putNotFirstStart(true)
            (activity as? MainActivity)?.showFragment(MainFragment.newInstance())
        }

        fab.setOnClickListener {
            (activity as? MainActivity)?.showFragment(TaskBuilderFragment())
        }
    }

    fun cancelTask(taskVO: ManageTaskVO) {
        adapter.deleteTask(taskVO)
        tasks.remove(taskVO)
    }


    fun infoTask(taskVO: ManageTaskVO) {
        (activity as? MainActivity)?.showFragment(ArticalWashingHeadFragment())
    }

    fun editTask(taskVO: ManageTaskVO) {
        (activity as? MainActivity)?.showFragment(TaskBuilderFragment(
            NewTaskVO(
                title = taskVO.title,
                description = taskVO.description,
                period = taskVO.whenExecute,
                type = taskVO.type
            )
        ))
    }

    fun fideTask(taskVO: ManageTaskVO) {
        adapter.selectTask(taskVO)
    }

    fun addTask(taskVO: NewTaskVO) {
        val task = ManageTaskVO(
            UUID.randomUUID().toString(),
            taskVO.title,
            taskVO.type,
            taskVO.description,
            taskVO.period
        )
        tasks.add(task)
        adapter.addTask(task)
    }

    private fun setupRv() {
        adapter = RvManageTaskAdapter()
        adapter.fragment = this
        rv_tasks.layoutManager = LinearLayoutManager(requireContext())
        rv_tasks.adapter = adapter
    }

    private fun setupLoginMode() {
        btn_next.visibility = View.VISIBLE
        iv_plan.setImageResource(R.drawable.ic_plan_health)
        (activity as? MainActivity)?.hideBottomNavigationBar()
    }

    private fun setupDefaultMode() {
        btn_next.visibility = View.GONE
        iv_plan.setImageResource(R.drawable.ic_arrow_left)
        (activity as? MainActivity)?.showBottomNavigationBar()
        iv_plan.setOnClickListener { (activity as MainActivity).closeFragment() }
    }
}
