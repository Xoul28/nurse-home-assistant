package com.dopezebraevm.nursehomeassistant.view.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.AppData
import com.dopezebraevm.nursehomeassistant.data.LocaleResolver
import kotlinx.android.synthetic.main.fragment_login_second_step.*

class LoginSecondStepFragment : BaseFragment(R.layout.fragment_login_second_step) {

    private lateinit var adapter: DiagnosRVAdater
    private lateinit var text: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text = ""

        et_diagnos.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                AppData.account.diagnosis = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        btn_next.setOnClickListener {
            (activity as MainActivity).showFragment(LoginQuestionnaireStepFragment())
        }

        adapter = DiagnosRVAdater {
            et_diagnos.setText(it)
        }
        rv_diagnos_list.adapter = adapter
        adapter.setupData(LocaleResolver.getIllList())
        adapter.notifyDataSetChanged()
    }
}