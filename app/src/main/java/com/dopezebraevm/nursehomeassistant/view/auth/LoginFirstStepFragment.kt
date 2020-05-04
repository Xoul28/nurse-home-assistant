package com.dopezebraevm.nursehomeassistant.view.auth

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.DatePicker
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.AppData
import kotlinx.android.synthetic.main.fragment_login_first_step.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*


class LoginFirstStepFragment : BaseFragment(R.layout.fragment_login_first_step), DatePickerDialog.OnDateSetListener {

    private lateinit var datePickerDialog: DatePickerDialog
    private var day: Int = -1
    private var month: Int = -1
    private var year: Int = -1
    private lateinit var calendar: Calendar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()
        year = 1980

        month = 1
        day = 1
        showDate(year, month, day)

        datePickerDialog = DatePickerDialog(
            requireContext(), this, year, month, day
        )

        cl_date_of_birth_container.setOnClickListener { datePickerDialog.show() }

        et_account_fl.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                AppData.account.fioUserName = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        btn_next.setOnClickListener {
            (activity as MainActivity).showFragment(LoginSecondStepFragment())
        }
    }

    private fun showDate(year: Int, month: Int, day: Int) {
        val date = StringBuilder().append(day).append(" ")
            .append(getMounth(month)).append(" ").append(year)
        tv_date_content.text = date
        AppData.account.dateOfBirth = date.toString()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        showDate(year, month, dayOfMonth)
    }

    private fun getMounth(month: Int) : String {
        return when (month) {
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
    }
}