package com.dopezebraevm.nursehomeassistant.view.help

import android.os.Bundle
import android.view.View
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.fragment_articale.*

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class ArticalWashingHeadFragment : BaseFragment(R.layout.fragment_articale_washing_head) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}