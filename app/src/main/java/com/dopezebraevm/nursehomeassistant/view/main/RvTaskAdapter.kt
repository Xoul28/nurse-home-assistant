package com.dopezebraevm.nursehomeassistant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.item_task.view.*

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
class RvTaskAdapter : RecyclerView.Adapter<RvTaskAdapter.TaskVH>() {

    private val tasks: MutableList<TaskVO> = ArrayList()
    private var selectable: TaskVO? = null
    lateinit var fragment: MainFragment
    lateinit var pagerVH: VpAdapter.PagerVH

    fun setTasks(tasks: List<TaskVO>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        if (tasks.isNotEmpty()) selectable = tasks[0]
        notifyDataSetChanged()
    }

    fun selectTask(task: TaskVO) {
        val isTask = selectable == task
        val lastSelectablePosition = tasks.indexOfFirst { it == selectable }
        selectable = if (isTask) null else task
        notifyItemChanged(lastSelectablePosition)
        if (!isTask) {
            val newPosition = tasks.indexOfFirst { it == task }
            notifyItemChanged(newPosition)
        }
    }

    fun deleteTask(task: TaskVO) {
        val lastSelectablePosition = tasks.indexOfFirst { it == selectable }
        tasks.removeAt(lastSelectablePosition)
        notifyItemRemoved(lastSelectablePosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskVH.newInstance(parent, fragment, pagerVH)

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val task = tasks[position]
        holder.onBind(task, task == selectable)
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
            itemView.tv_date.text = vo.whenExecute
            if (isSelectable) {
                itemView.ll_btn.visibility = View.VISIBLE
                itemView.tv_description.visibility = View.VISIBLE
                itemView.tv_description.text = vo.description
            } else {
                itemView.ll_btn.visibility = View.GONE
                itemView.tv_description.visibility = View.GONE
            }
            itemView.btn_next.setOnClickListener {
                fragment.executeTask(vo)
                pagerVH.executeTask(vo)
            }
            itemView.btn_skip.setOnClickListener {
                fragment.skipTask(vo)
                pagerVH.skipTask(vo)
            }
            itemView.setOnClickListener {
                pagerVH.fideTask(vo)
                fragment.fideTask(vo)
            }
        }
    }

}