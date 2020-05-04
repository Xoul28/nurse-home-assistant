package com.dopezebraevm.nursehomeassistant.view.dairy

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dopezebraevm.nursehomeassistant.App
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.fragment_dairy.*
import java.util.*

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class DairyFragment : BaseFragment(R.layout.fragment_dairy) {

    companion object {

        fun newInstance(): DairyFragment {
            return DairyFragment()
        }
    }

    lateinit var adapter: RvDairyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.showBottomNavigationBar()
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.aqua_haze)
        setupRv()

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mycal: Calendar = GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dayOfMonth)

        addData()
    }

    override fun onStart() {
        super.onStart()
    }

    fun selectDairy(dairy: DairyVO) {
        adapter.selectDairy(dairy)
    }

    private fun createPoint(point: Int, day: Int, cal: Calendar): Point {
        cal.set(Calendar.DAY_OF_MONTH, day)
        return Point(point, cal.time)
    }

    private fun setupRv() {
        adapter = RvDairyAdapter()
        adapter.fragment = this
        rv_dairy.layoutManager = LinearLayoutManager(requireContext())
        rv_dairy.adapter = adapter
    }

    fun addData() {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mycal: Calendar = GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dayOfMonth)


        if (!App.get(requireContext()).prefHelper.isGraph()) {
            val points = listOf(
                createPoint(3, 4, mycal),
                createPoint(5, 5, mycal),
                createPoint(2, 6, mycal),
                createPoint(1, 7, mycal),
                createPoint(7, 8, mycal),
                createPoint(6, 9, mycal),
                createPoint(8, 10, mycal)
            )
            adapter.setDairyList(listOf(
                DairyVO("Интенсивность боли", "7", listOf("8 (10.05)", "6 (9.05)", "7 (8.05)"), points),
                DairyVO("Артериальное давление", "110", listOf("134/52 (9.05)", "251/62 (8.05)", "165/42 (7.05)"), listOf(Point(1, Date())))
            ))
            App.get(requireContext()).prefHelper.putGraph(true)
        } else {
            val points = listOf(
                createPoint(3, 4, mycal),
                createPoint(5, 5, mycal),
                createPoint(2, 6, mycal),
                createPoint(1, 7, mycal),
                createPoint(7, 8, mycal),
                createPoint(6, 9, mycal),
                createPoint(8, 10, mycal),
                createPoint(6, 11, mycal),
                createPoint(4, 12, mycal),
                createPoint(5, 13, mycal),
                createPoint(3, 14, mycal),
                createPoint(2, 15, mycal)
            )
            adapter.setDairyList(listOf(
                DairyVO("Интенсивность боли", "3", listOf("2 (15.05)", "3 (14.05)", "5 (13.05)"), points),
                DairyVO("Артериальное давление", "143", listOf("180/73 (15.05)", "140/70 (14.05)", "172/57 (13.05)"), listOf(Point(1, Date())))
            ))
            App.get(requireContext()).prefHelper.putGraph(false)
        }
    }
}
