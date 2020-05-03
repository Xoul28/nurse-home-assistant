package com.dopezebraevm.nursehomeassistant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.item_calendar.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
class RvCalendarAdapter : RecyclerView.Adapter<RvCalendarAdapter.CalendarVH>() {

    private val calendars: MutableList<CalendarVO> = ArrayList()
    private lateinit var selected: CalendarVO
    lateinit var fragment: MainFragment

    fun setCalendarList(calendars: List<CalendarVO>) {
        this.calendars.clear()
        this.calendars.addAll(calendars)
        val date = Calendar.getInstance().time
        selected = calendars.first { it.id == date.date }
        notifyDataSetChanged()
    }

    fun selectTask(calendar: CalendarVO) {
        if (selected == calendar) return
        val lastSelectablePosition = calendars.indexOfFirst { it == selected }
        notifyItemChanged(lastSelectablePosition)
        val newPosition = calendars.indexOfFirst { it == calendar }
        if (newPosition == -1) return
        selected = calendar
        notifyItemChanged(newPosition)
    }

    fun selectTask(day: Int) {
        if (selected.id == day) return
        val lastSelectablePosition = calendars.indexOfFirst { it == selected }
        notifyItemChanged(lastSelectablePosition)
        val newPosition = calendars.indexOfFirst { it.id == day }
        if (newPosition == -1) return
        val calendar = calendars[newPosition]
        selected = calendar
        notifyItemChanged(newPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CalendarVH.newInstance(parent, fragment)

    override fun onBindViewHolder(holder: CalendarVH, position: Int) {
        holder.onBind(calendars[position], calendars[position] == selected)
    }

    override fun getItemCount(): Int = calendars.size

    class CalendarVH(
        itemView: View,
        private val fragment: MainFragment
    ) : RecyclerView.ViewHolder(itemView) {

        companion object {

            fun newInstance(parent: ViewGroup, fragment: MainFragment): CalendarVH {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_calendar, parent, false)
                return CalendarVH(view, fragment)
            }
        }

        fun onBind(vo: CalendarVO, isSelected: Boolean) {
            itemView.tv_calendar_item.text = vo.id.toString()
            itemView.cl_calendar_item.setOnClickListener { fragment.onClickCalendarItem(vo) }
            if (isSelected) {
                itemView.cl_calendar_item.setBackgroundResource(R.drawable.bg_calendar_selected)
                itemView.tv_calendar_item.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            } else {
                itemView.cl_calendar_item.setBackgroundResource(R.drawable.bg_calendar)
                itemView.tv_calendar_item.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            }
        }
    }

}