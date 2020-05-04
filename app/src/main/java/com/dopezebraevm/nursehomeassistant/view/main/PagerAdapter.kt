package com.dopezebraevm.nursehomeassistant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.item_main_pager.view.*
import java.util.*

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
class VpAdapter : RecyclerView.Adapter<VpAdapter.PagerVH>() {

    lateinit var fragment: MainFragment
    private var days: List<DayVO> = Collections.emptyList()

    fun setDays(days: List<DayVO>) {
        this.days = days
        notifyDataSetChanged()
    }

    fun addTaskInDay(task: TaskVO, day: Int) {
        var updateIndex = -1
        this.days = this.days.mapIndexed { index, dayVO ->
            if (dayVO.day == day) {
                val tasks = dayVO.tasks.toMutableList()
                tasks.add(task)
                updateIndex = index
                DayVO(
                    dayVO.day,
                    tasks
                )
            } else dayVO
        }
        if (updateIndex != -1) notifyItemChanged(updateIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        return PagerVH.newInstance(parent, fragment)
    }

    override fun getItemCount() = days.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.onBind(days[position])
    }

    class PagerVH(
        itemView: View,
        private val fragment: MainFragment
    ) : RecyclerView.ViewHolder(itemView) {

        companion object {

            fun newInstance(parent: ViewGroup, fragment: MainFragment): PagerVH {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_main_pager, parent, false)
                return PagerVH(view, fragment)
            }
        }

        lateinit var adapter: RvTaskAdapter

        fun onBind(vo: DayVO) {
            setupRv()
            adapter.setTasks(vo.tasks)
        }

        fun executeTask(task: TaskVO) {
            adapter.deleteTask(task)
            adapter.enableSeparator()
            adapter.addTask(task)
            adapter.selectFirst()
        }

        fun skipTask(task: TaskVO) {
            adapter.deleteTask(task)
            adapter.enableSeparator()
            adapter.addTask(task)
            adapter.selectFirst()
        }

        fun fideTask(taskVO: TaskVO) {
            adapter.selectTask(taskVO)
        }

        private fun setupRv() {
            adapter = RvTaskAdapter()
            adapter.fragment = fragment
            adapter.pagerVH = this
            itemView.rv_tasks.layoutManager = LinearLayoutManager(itemView.context)
            itemView.rv_tasks.adapter = adapter
        }
    }
}