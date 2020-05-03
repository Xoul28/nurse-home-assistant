package com.dopezebraevm.nursehomeassistant.view.task

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.fragment_create_new_task.view.*
import kotlinx.android.synthetic.main.item_template_task.view.*
import kotlinx.android.synthetic.main.item_template_task.view.tv_title
import java.util.zip.Inflater

class TaskTemplateRVAdapter(var listener: (TaskTemplateVO)-> Unit?) : RecyclerView.Adapter<TaskTemplateRVAdapter.ViewHolder>() {

    private var taskTemplateVO: List<TaskTemplateVO> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(taskTemplateVO: TaskTemplateVO) {
            itemView.tv_title.text = taskTemplateVO.title
            itemView.tv_date.text = taskTemplateVO.period
            itemView.setOnClickListener {
                listener.invoke(taskTemplateVO)
            }
        }

    }

    fun setupTemplates(taskTemplateVO: List<TaskTemplateVO>) {
        this.taskTemplateVO = taskTemplateVO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_template_task, parent,false))
    override fun getItemCount() = taskTemplateVO.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(taskTemplateVO[position])
}