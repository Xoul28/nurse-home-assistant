package com.dopezebraevm.nursehomeassistant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.view.TaskVO.Companion.COMPLETE
import com.dopezebraevm.nursehomeassistant.view.TaskVO.Companion.EXECUTE
import com.dopezebraevm.nursehomeassistant.view.TaskVO.Companion.SEPARATOR
import com.dopezebraevm.nursehomeassistant.view.TaskVO.Companion.SKIP
import com.dopezebraevm.nursehomeassistant.view.TaskVO.Companion.TASK
import kotlinx.android.synthetic.main.item_task.view.*

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
class RvTaskAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tasks: MutableList<TaskVO> = ArrayList()
    private var selectable: TaskVO? = null
    lateinit var fragment: MainFragment
    lateinit var pagerVH: VpAdapter.PagerVH

    fun setTasks(tasks: List<TaskVO>) {
        this.tasks.clear()
        tasks.forEach { addTask(it) }
        if (tasks.isNotEmpty()) selectable = tasks[0]
        notifyDataSetChanged()
    }

    fun enableSeparator() {
        if (tasks.any { it.type == SEPARATOR }) return
        this.tasks.add(TaskVO("separator", SEPARATOR))
        notifyItemChanged(this.tasks.lastIndex)
    }

    fun selectTask(task: TaskVO) {
        if (task.completeType != EXECUTE || task.type == SEPARATOR) return
        val isTask = selectable == task
        val lastSelectablePosition = tasks.indexOfFirst { it == selectable }
        selectable = if (isTask) null else task
        notifyItemChanged(lastSelectablePosition)
        if (!isTask) {
            val newPosition = tasks.indexOfFirst { it == task }
            notifyItemChanged(newPosition)
        }
    }

    fun selectFirst() {
        val task = tasks.firstOrNull() ?: return
        selectTask(task)
    }

    fun deleteTask(task: TaskVO) {
        val lastSelectablePosition = tasks.indexOfFirst { it == task }
        tasks.removeAt(lastSelectablePosition)
        if (task == selectable) selectable = null
        notifyItemRemoved(lastSelectablePosition)
    }

    fun addTask(task: TaskVO) {
        if (tasks.contains(task)) return
        val index = tasks.indexOfFirst { it.type == SEPARATOR }
        if (index == -1) {
            if (task.completeType != EXECUTE) {
                tasks.add(TaskVO("separator", SEPARATOR))
                notifyItemChanged(tasks.size)
            }
            tasks.add(task)
            notifyItemChanged(tasks.size)
        } else if (task.completeType == EXECUTE) {
            tasks.add(index, task)
            notifyItemChanged(index)
        } else {
            tasks.add(task)
            notifyItemChanged(tasks.size)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = tasks[position]
        return item.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TASK -> TaskVH.newInstance(parent, fragment, pagerVH)
            else -> SeparatorVH.newInstance(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = tasks[position]
        (holder as? TaskVH)?.onBind(task, task == selectable)
    }

    override fun getItemCount(): Int = tasks.size

    class TaskVH(itemView: View,
                 private val fragment: MainFragment,
                 private val pagerVH: VpAdapter.PagerVH) : RecyclerView.ViewHolder(itemView) {

        companion object {

            fun newInstance(parent: ViewGroup, fragment: MainFragment,
                            pagerVH: VpAdapter.PagerVH): TaskVH {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_task, parent, false)
                return TaskVH(view, fragment, pagerVH)
            }
        }

        fun onBind(vo: TaskVO, isSelectable: Boolean) {
            itemView.tv_title.text = vo.title
            if (vo.completeType == EXECUTE) {
                itemView.tv_date.text = vo.whenExecute
            } else {
                itemView.tv_date.text = if (vo.completeType == COMPLETE) "выполнено"
                else "пропущено"
            }
            if (vo.completeType == EXECUTE) {
                itemView.tv_title.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                itemView.tv_date.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                itemView.iv_info.setImageResource(R.drawable.ic_info_ber)
            } else {
                itemView.tv_title.setTextColor(ContextCompat.getColor(itemView.context, R.color.gray8E))
                itemView.tv_date.setTextColor(ContextCompat.getColor(itemView.context, R.color.gray8E))
                if (vo.completeType == SKIP) itemView.iv_info.setImageResource(R.drawable.ic_skip_task)
                else itemView.iv_info.setImageResource(R.drawable.ic_complete_task)
            }
            if (isSelectable && vo.completeType == EXECUTE) {
                itemView.ll_btn.visibility = View.VISIBLE
                itemView.tv_description.visibility = if (vo.description.isBlank()) View.GONE else View.VISIBLE
                itemView.tv_description.text = vo.description
            } else {
                itemView.ll_btn.visibility = View.GONE
                itemView.tv_description.visibility = View.GONE
            }
            itemView.btn_next.setOnClickListener {
                vo.completeType = COMPLETE
                fragment.executeTask(vo)
                pagerVH.executeTask(vo)
            }
            itemView.btn_skip.setOnClickListener {
                vo.completeType = SKIP
                fragment.skipTask(vo)
                pagerVH.skipTask(vo)
            }
            itemView.setOnClickListener {
                pagerVH.fideTask(vo)
                fragment.fideTask(vo)
            }
        }
    }

    class SeparatorVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {

            fun newInstance(parent: ViewGroup): SeparatorVH {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_tasks_completed, parent, false)
                return SeparatorVH(view)
            }
        }
    }

}
