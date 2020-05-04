package com.dopezebraevm.nursehomeassistant.view.help

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.view.calls.CallFragment
import com.dopezebraevm.nursehomeassistant.view.encyclopedia.EncyclopediaFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_help.*

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class HelpFragment : BaseFragment(R.layout.fragment_help) {

    lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.showBottomNavigationBar()
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.aqua_haze)
        adapter = Adapter(this)
        pager.adapter = adapter
        TabLayoutMediator(tab_layout, pager) { tab, position ->
            tab.text = if (position == 0) "Полезные контакты"
            else "Энциклопедия"
        }.attach()
    }
}

class Adapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) CallFragment()
        else EncyclopediaFragment()
    }
}