package com.dopezebraevm.nursehomeassistant.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.dopezebraevm.nursehomeassistant.App
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.db.TaskDB
import com.dopezebraevm.nursehomeassistant.view.TaskVO.Companion.EXECUTE
import com.dopezebraevm.nursehomeassistant.view.TaskVO.Companion.TASK
import com.dopezebraevm.nursehomeassistant.view.indicators.MeasurePressureFragment
import com.dopezebraevm.nursehomeassistant.view.indicators.PainFragment
import com.dopezebraevm.nursehomeassistant.view.plan.CreatePlanFragment
import com.dopezebraevm.nursehomeassistant.view.task.NewTaskVO
import com.dopezebraevm.nursehomeassistant.view.task.TaskBuilderFragment
import kotlinx.android.synthetic.main.fragment_create_plan.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.fab
import kotlinx.android.synthetic.main.fragment_main.iv_plan
import kotlinx.android.synthetic.main.fragment_main.textView
import java.util.*

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
class MainFragment : BaseFragment(R.layout.fragment_main) {

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    lateinit var adapter: VpAdapter
    lateinit var calendarAdapter: RvCalendarAdapter
    lateinit var calendarLayoutManager: LinearLayoutManager
    lateinit var days: List<DayVO>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.showBottomNavigationBar()
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.aqua_haze)
        setupViewPager()
        setupRv()

        val tasks = App.get(requireContext()).dataBase.getTaskDao().getAll().sortedBy { it.date }
        val firstDate = tasks.getOrNull(0)?.date ?: Date().time
        val firstDay = Date(firstDate).date

        setupCalendar(firstDay)
        setupDays(tasks)

        val date = Calendar.getInstance().time
        setupMounth(date.month)
        val selected = days.indexOfFirst { it.day == date.date }
        vp_day.setCurrentItem(selected, false)
        vp_day.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                calendarAdapter.selectTask(days[position].day)
                calendarLayoutManager.scrollToPositionWithOffset(position, 450)
            }
        })

        iv_plan.setOnClickListener {
            (activity as? MainActivity)?.showFragment(CreatePlanFragment.newInstance())
        }

        textView.setOnClickListener {
            (activity as? MainActivity)?.showFragment(CreatePlanFragment.newInstance())
        }
        fab.setOnClickListener {
            (activity as? MainActivity)?.showFragment(TaskBuilderFragment())
        }
    }

    fun onClickCalendarItem(calendarVO: CalendarVO) {
        calendarAdapter.selectTask(calendarVO)
        val index = days.indexOfFirst { it.day == calendarVO.id }
        if (index == -1) return
        vp_day.currentItem = index
    }

    fun executeTask(task: TaskVO) {
        if (task.taskType == "pain_level") {
            (activity as? MainActivity)?.showFragment(PainFragment())
        } else if (task.taskType == "pressure") {
            (activity as? MainActivity)?.showFragment(MeasurePressureFragment())
        }
    }

    fun skipTask(task: TaskVO) {

    }

    fun fideTask(task: TaskVO) {

    }

    fun addTask(task: NewTaskVO) {
        val date = days.getOrNull(vp_day.currentItem) ?: return
        adapter.addTaskInDay(
            TaskVO(
                UUID.randomUUID().toString(),
                TASK,
                task.title,
                task.description,
                task.period,
                task.type,
                EXECUTE
            ),
            date.day
        )
    }

    private fun setupViewPager() {
        adapter = VpAdapter()
        adapter.fragment = this
        vp_day.adapter = adapter
    }

    private fun setupRv() {
        calendarAdapter = RvCalendarAdapter()
        calendarAdapter.fragment = this
        calendarLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_calendar.layoutManager = calendarLayoutManager
        rv_calendar.adapter = calendarAdapter
    }

    private fun setupMounth(month: Int) {
        val text = when (month) {
            0 -> "Январь"
            1 -> "Февраль"
            2 -> "Март"
            3 -> "Апрель"
            4 -> "Май"
            5 -> "Июнь"
            6 -> "Июль"
            7 -> "Август"
            8 -> "Сентябрь"
            9 -> "Октябрь"
            10 -> "Ноябрь"
            11 -> "Декабрь"
            else -> "Январь"
        }
        tv_mounth.text = text
    }

    private fun getDaysOfMounth(): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mycal: Calendar = GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dayOfMonth)
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun setupCalendar(startDay: Int) {
        val daysOfMounth = getDaysOfMounth()
        val calendars = ArrayList<CalendarVO>()
        for (i in 1 .. daysOfMounth) {
            calendars.add(CalendarVO(i, i >= startDay))
        }
        calendarAdapter.setCalendarList(calendars)
    }

    private fun setupDays(tasks: List<TaskDB>) {
        val map = tasks.groupBy({ Date(it.date).date }, { it })
        days = map.map {
            DayVO(
                day = it.key,
                tasks = it.value.map { task ->
                    TaskVO(
                        id = task.taskId,
                        title = task.title,
                        type = TASK,
                        description = task.description,
                        whenExecute = task.whenExecute,
                        taskType = task.type,
                        completeType = task.completedType
                    )
                }
            )
        }
        adapter.setDays(days)
    }
}
