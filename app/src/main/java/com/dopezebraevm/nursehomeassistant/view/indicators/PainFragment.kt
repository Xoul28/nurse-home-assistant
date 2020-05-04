package com.dopezebraevm.nursehomeassistant.view.indicators

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.db.DataBaseBuilderRoom
import com.dopezebraevm.nursehomeassistant.data.db.LevelPainDB
import com.dopezebraevm.nursehomeassistant.data.db.PlanDB
import com.dopezebraevm.nursehomeassistant.data.db.TaskDB
import com.rtugeek.android.colorseekbar.ColorSeekBar
import kotlinx.android.synthetic.main.fragment_pain.*
import java.util.*
import kotlin.collections.ArrayList

class PainFragment : Fragment(R.layout.fragment_pain) {

    private var value: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorSlider.setColorSeeds(R.array.gradient_colors)
        colorSlider.setOnColorChangeListener {
                colorBarPosition, alphaBarPosition, color -> value = colorBarPosition
        }

        btn_next.setOnClickListener {
            DataBaseBuilderRoom(requireContext())
                .build()
                .getLevelPainDao()
                .insert(
                    LevelPainDB(
                        level = value
                    )
                )

            activity?.onBackPressed()
        }

        cv_doctor.setOnClickListener {
            DataBaseBuilderRoom(requireContext())
                .build()
                .getTaskDao()
                .insert(
                    createTask()
                )
        }
    }

    fun createTask() : TaskDB {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mycal: Calendar =
            GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dayOfMonth)
        mycal.set(Calendar.DAY_OF_MONTH, dayOfMonth + 3)

        return  TaskDB(
                    taskId = UUID.randomUUID().toString(),
                    title = "Обратиться к врачу",
                    type = "Доктор",
                    description = "Записаться к врачу для обследования",
                    whenExecute = "на этой недели",
                    completedType = 0,
                    date = mycal.time.time
                )
    }
}