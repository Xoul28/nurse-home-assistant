package com.dopezebraevm.nursehomeassistant.view.dairy

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
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

        val points = listOf(
            Point(1, Date()),
            Point(3, Date()),
            Point(5, Date()),
            Point(4, Date()),
            Point(7, Date()),
            Point(3, Date()),
            Point(1, Date())
        )
        adapter.setDairyList(listOf(
            DairyVO("Title1", "110", listOf("1", "2", "3"), points),
            DairyVO("Title2", "110", listOf("1", "2", "3"), listOf(Point(1, Date()))),
            DairyVO("Title3", "110", listOf("1", "2", "3"), listOf(Point(1, Date()))),
            DairyVO("Title4", "110", listOf("1", "2", "3"), listOf(Point(1, Date())))
        ))
    }

    fun selectDairy(dairy: DairyVO) {
        adapter.selectDairy(dairy)
    }

    private fun setupRv() {
        adapter = RvDairyAdapter()
        adapter.fragment = this
        rv_dairy.layoutManager = LinearLayoutManager(requireContext())
        rv_dairy.adapter = adapter
    }
}
