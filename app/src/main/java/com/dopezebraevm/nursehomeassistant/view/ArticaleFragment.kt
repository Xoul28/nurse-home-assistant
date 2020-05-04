package com.dopezebraevm.nursehomeassistant.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.fragment_articale.*
import kotlinx.android.synthetic.main.fragment_pain.*

class ArticaleFragment : Fragment(R.layout.fragment_articale) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}