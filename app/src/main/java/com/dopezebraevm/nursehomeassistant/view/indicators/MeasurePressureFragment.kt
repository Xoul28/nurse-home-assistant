package com.dopezebraevm.nursehomeassistant.view.indicators

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.dopezebraevm.nursehomeassistant.BaseFragment
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.db.AppDatabase
import com.dopezebraevm.nursehomeassistant.data.db.DataBaseBuilderRoom
import com.dopezebraevm.nursehomeassistant.data.db.MeasurePressureDB
import kotlinx.android.synthetic.main.alert_picker.view.*
import kotlinx.android.synthetic.main.fragment_create_new_task.*
import kotlinx.android.synthetic.main.fragment_measure_pressure.*

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class MeasurePressureFragment : BaseFragment(R.layout.fragment_measure_pressure) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ll_pressure_first_container.setOnClickListener {
            showAlertFirstField()
        }

        ll_pressure_second_container.setOnClickListener {
            showAlertSecondField()
        }

        btn_next.setOnClickListener {
            DataBaseBuilderRoom(requireContext())
                .build()
                .getPressureDao()
                .insert(MeasurePressureDB(
                    topPressure = tv_pressure_first.text.toString().toInt(),
                    bottomPressure = tv_pressure_second.text.toString().toInt()
                    )
                )
        }
    }

    fun showAlertFirstField() {
        var dialog: AlertDialog? = null

        var newValue = 0
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Выберите верхнее АД")

        val customLayout: View =
            layoutInflater.inflate(R.layout.alert_picker, null)
        builder.setView(customLayout)

        builder.setPositiveButton("OK") { _, _ ->
            if (newValue > 0)
                tv_pressure_first.text = newValue.toString()
        }

        builder.setNegativeButton("Отмена") { _, _ ->
            dialog?.dismiss()
        }

        dialog = builder.create()
        dialog.show()

        customLayout.number_picker?.setOnValueChangedListener { picker, oldVal, newVal ->
            newValue = newVal
        }
        customLayout.number_picker?.minValue = 1
        customLayout.number_picker?.maxValue = 350
        customLayout.number_picker?.value = 120
    }

    fun showAlertSecondField() {
        var dialog: AlertDialog? = null

        var newValue = 0
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Выберите нижнее АД")

        val customLayout: View =
            layoutInflater.inflate(R.layout.alert_picker, null)
        builder.setView(customLayout)

        builder.setPositiveButton("OK") { _, _ ->
            if (newValue > 0)
                tv_pressure_second.text = newValue.toString()
        }

        builder.setNegativeButton("Отмена") { _, _ ->
            dialog?.dismiss()
        }

        dialog = builder.create()
        dialog.show()

        customLayout.number_picker?.setOnValueChangedListener { picker, oldVal, newVal ->
            newValue = newVal
        }
        customLayout.number_picker?.minValue = 1
        customLayout.number_picker?.maxValue = 200
        customLayout.number_picker?.value = 70
    }
}
