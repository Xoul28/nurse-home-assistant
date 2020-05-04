package com.dopezebraevm.nursehomeassistant.view.encyclopedia

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.EncyclopediaJson
import com.dopezebraevm.nursehomeassistant.data.LocaleResolver
import com.dopezebraevm.nursehomeassistant.view.ArticaleFragment
import kotlinx.android.synthetic.main.fragment_create_new_task.*
import kotlinx.android.synthetic.main.fragment_encyclopedia.*
import kotlinx.android.synthetic.main.fragment_encyclopedia.et_search

class EncyclopediaFragment : Fragment(R.layout.fragment_encyclopedia) {

    private lateinit var data: List<EncyclopediaJson>
    private  var searchData: MutableList<EncyclopediaJson> = mutableListOf()
    private lateinit var adapter: EncyclopediaRVAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.showBottomNavigationBar()
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)

        this.adapter = EncyclopediaRVAdapter {
            (activity as MainActivity).showFragment(ArticaleFragment())
        }
        data = LocaleResolver.getEncyclopediaList()
        rv_article.adapter = adapter
        adapter.setupData(data)
        adapter.notifyDataSetChanged()

        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isNotBlank() && data.size > 0) {
                    adapter.setupData(data)
                    adapter.notifyDataSetChanged()
                    searchData.clear()
                }
                searchData.addAll( data.filter { it.title.contains(p0.toString(),true) } )
                if (searchData.size > 0) {
                    adapter.setupData(searchData)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }
}