package com.dopezebraevm.nursehomeassistant.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    lateinit var adapter: VpAdapter
    lateinit var calendarAdapter: RvCalendarAdapter
    lateinit var days: List<DayVO>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.aqua_haze);
        setupViewPager()
        setupRv()

        calendarAdapter.setCalendarList(listOf(
            CalendarVO(1),
            CalendarVO(2),
            CalendarVO(3),
            CalendarVO(4),
            CalendarVO(5),
            CalendarVO(6),
            CalendarVO(7),
            CalendarVO(8),
            CalendarVO(9),
            CalendarVO(10),
            CalendarVO(11),
            CalendarVO(12),
            CalendarVO(13),
            CalendarVO(14),
            CalendarVO(15),
            CalendarVO(16),
            CalendarVO(17),
            CalendarVO(18),
            CalendarVO(19),
            CalendarVO(20),
            CalendarVO(21),
            CalendarVO(22),
            CalendarVO(23),
            CalendarVO(24),
            CalendarVO(25),
            CalendarVO(26),
            CalendarVO(27),
            CalendarVO(28),
            CalendarVO(29),
            CalendarVO(30),
            CalendarVO(31)
        ))

        days = listOf(
            DayVO(1, listOf(TaskVO("1", "Test1", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(2, listOf(TaskVO("11", "Test2", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(3, listOf(TaskVO("12", "Test3", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(4, listOf(TaskVO("13", "Test4", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(5, listOf(TaskVO("14", "Test5", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(6, listOf(TaskVO("15", "Test6", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(7, listOf(TaskVO("16", "Test7", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(8, listOf(TaskVO("17", "Test8", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(9, listOf(TaskVO("18", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(10, listOf(TaskVO("19", "Test10", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(11, listOf(TaskVO("20", "Test11", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(12, listOf(TaskVO("21", "Test12", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(13, listOf(TaskVO("22", "Test13", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(14, listOf(TaskVO("23", "Test14", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(15, listOf(TaskVO("24", "Test15", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(16, listOf(TaskVO("25", "Test16", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(17, listOf(TaskVO("26", "Test17", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(18, listOf(TaskVO("27", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(19, listOf(TaskVO("28", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(20, listOf(TaskVO("29", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(21, listOf(TaskVO("30", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(22, listOf(TaskVO("31", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(23, listOf(TaskVO("32", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(24, listOf(TaskVO("33", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(25, listOf(TaskVO("34", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(26, listOf(TaskVO("35", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(27, listOf(TaskVO("36", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(28, listOf(TaskVO("37", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(29, listOf(TaskVO("38", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(30, listOf(TaskVO("39", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day"))),
            DayVO(31, listOf(TaskVO("40", "Test9", "description", "23 day"), TaskVO("2", "Test1", "description", "23 day"), TaskVO("3", "Test1", "description", "23 day")))
        )

        adapter.setDays(days)

        val date = Calendar.getInstance().time
        val selected = days.indexOfFirst { it.day == date.date }
        vp_day.setCurrentItem(selected, false)
    }

    fun onClickCalendarItem(calendarVO: CalendarVO) {
        calendarAdapter.selectTask(calendarVO)
        val index = days.indexOfFirst { it.day == calendarVO.id }
        if (index == -1) return
        vp_day.currentItem = index
    }

    fun executeTask(task: TaskVO) {

    }

    fun skipTask(task: TaskVO) {

    }

    fun fideTask(task: TaskVO) {

    }

    private fun setupViewPager() {
        adapter = VpAdapter()
        adapter.fragment = this
        vp_day.adapter = adapter
    }

    private fun setupRv() {
        calendarAdapter = RvCalendarAdapter()
        calendarAdapter.fragment = this
        rv_calendar.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_calendar.adapter = calendarAdapter
    }
}
