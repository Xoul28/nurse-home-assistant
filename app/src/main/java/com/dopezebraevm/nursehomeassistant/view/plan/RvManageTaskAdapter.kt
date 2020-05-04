package com.dopezebraevm.nursehomeassistant.view.plan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.item_manage_task.view.*

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class RvManageTaskAdapter : RecyclerView.Adapter<RvManageTaskAdapter.ManageTaskVH>() {

    private val tasks: MutableList<ManageTaskVO> = ArrayList()
    private var selectable: ManageTaskVO? = null
    lateinit var fragment: CreatePlanFragment

    fun setTasks(tasks: List<ManageTaskVO>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        if (tasks.isNotEmpty()) selectable = tasks[0]
        notifyDataSetChanged()
    }

    fun selectTask(task: ManageTaskVO) {
//        val isTask = selectable == task
//        val lastSelectablePosition = tasks.indexOfFirst { it == selectable }
//        selectable = if (isTask) null else task
//        notifyItemChanged(lastSelectablePosition)
//        if (!isTask) {
//            val newPosition = tasks.indexOfFirst { it == task }
//            notifyItemChanged(newPosition)
//        }
    }

    fun deleteTask(task: ManageTaskVO) {
        val lastSelectablePosition = tasks.indexOfFirst { it == task }
        val isLastItem = lastSelectablePosition == tasks.lastIndex
        tasks.removeAt(lastSelectablePosition)
        if (task == selectable) selectable = null
        notifyItemRemoved(lastSelectablePosition)
        if (isLastItem && tasks.isNotEmpty()) notifyItemChanged(tasks.lastIndex)
    }

    fun addTask(task: ManageTaskVO) {
        if (tasks.contains(task)) return
        tasks.add(task)
        if (tasks.size > 1) {
            notifyItemRangeChanged(tasks.lastIndex - 1, tasks.lastIndex)
        } else {
            notifyItemChanged(tasks.lastIndex)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageTaskVH {
        return ManageTaskVH.newInstance(parent, fragment)
    }

    override fun onBindViewHolder(holder: ManageTaskVH, position: Int) {
        val task = tasks[position]
        holder.onBind(task, task == selectable, position == tasks.lastIndex)
    }

    override fun getItemCount(): Int = tasks.size

    class ManageTaskVH(
        itemView: View,
        private val fragment: CreatePlanFragment
    ) : RecyclerView.ViewHolder(itemView) {

        companion object {

            fun newInstance(
                parent: ViewGroup, fragment: CreatePlanFragment
            ): ManageTaskVH {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_manage_task, parent, false)
                return ManageTaskVH(view, fragment)
            }
        }

        fun onBind(vo: ManageTaskVO, isSelectable: Boolean, isLast: Boolean) {
            itemView.tv_title.text = vo.title
            itemView.tv_title_center.text = vo.title
            itemView.tv_description.text = vo.description
            itemView.tv_date.text = vo.whenExecute
            itemView.tv_date_center.text = vo.whenExecute
            if (isLast) {
                itemView.shadow.visibility = View.VISIBLE
                //itemView.margin.visibility = View.VISIBLE
            } else {
                itemView.shadow.visibility = View.GONE
                //itemView.margin.visibility = View.GONE
            }
            if (true) { //TODO
                itemView.tv_title.visibility = View.VISIBLE
                if (vo.description.isBlank()) {
                    itemView.tv_description.visibility = View.GONE
                } else {
                    itemView.tv_description.visibility = View.VISIBLE
                }
                itemView.tv_date.visibility = View.VISIBLE
                itemView.cl_btn.visibility = View.VISIBLE
                itemView.tv_title_center.visibility = View.GONE
                itemView.tv_date_center.visibility = View.GONE
            } else {
                itemView.tv_title_center.visibility = View.VISIBLE
                itemView.tv_date_center.visibility = View.VISIBLE
                itemView.tv_title.visibility = View.GONE
                itemView.tv_description.visibility = View.GONE
                itemView.tv_date.visibility = View.GONE
                itemView.cl_btn.visibility = View.GONE
            }
            itemView.iv_cancel.setOnClickListener {
                fragment.cancelTask(vo)
            }
            itemView.tv_info.setOnClickListener {
                fragment.infoTask(vo)
            }
            itemView.tv_manage.setOnClickListener {
                fragment.editTask(vo)
            }
            itemView.cl_main_manage_task_block.setOnClickListener {
                fragment.fideTask(vo)
            }
        }
    }
}