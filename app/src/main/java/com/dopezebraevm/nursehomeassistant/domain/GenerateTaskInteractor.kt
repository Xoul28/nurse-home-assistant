package com.dopezebraevm.nursehomeassistant.domain

import android.os.AsyncTask
import com.dopezebraevm.nursehomeassistant.data.db.AppDatabase
import com.dopezebraevm.nursehomeassistant.data.db.PlanDB
import com.dopezebraevm.nursehomeassistant.data.db.TaskDB
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class GenerateTaskInteractor(private val appDatabase: AppDatabase) {

    fun generateSync() {
        AsyncTask.execute { generate() }
    }

    fun generate() {
        val plans = appDatabase.getPlanDao().getAll()
        if (plans?.isNotEmpty() != true) return
        appDatabase.getTaskDao().deleteAll()
        appDatabase.getTaskDao().insertList(createTasks(plans))
    }

    private fun createTasks(plans: List<PlanDB>): List<TaskDB> {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mycal: Calendar = GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dayOfMonth)
        val daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH)
        val tasks = ArrayList<TaskDB>()
        for (i in dayOfMonth .. daysInMonth) {
            mycal.set(Calendar.DAY_OF_MONTH, i)
            plans.forEach { tasks.addAll(createTask(it, mycal)) }
        }
        return tasks
    }

    private fun createTask(plan: PlanDB, mycal: Calendar): List<TaskDB> {
        return when (plan.whenExecute) {
            "Утром и вечером" -> {
                listOf(
                    createTaskFromPlan(plan, "Утром", mycal),
                    createTaskFromPlan(plan, "Вечером", mycal)
                )
            }
            "Утром, Днем и Вечером" -> {
                listOf(
                    createTaskFromPlan(plan, "Утром", mycal),
                    createTaskFromPlan(plan, "Днем", mycal),
                    createTaskFromPlan(plan, "Вечером", mycal)
                )
            }
            "Каждый день" -> {
                listOf(createTaskFromPlan(plan, "Днем", mycal))
            }
            "Раз в неделю" -> {
                emptyList()
            }
            else -> {
                listOf(createTaskFromPlan(plan, plan.whenExecute, mycal))
            }
        }
    }

    private fun createTaskFromPlan(plan: PlanDB, whenExecute: String, mycal: Calendar): TaskDB {
        return TaskDB(
            taskId = UUID.randomUUID().toString(),
            title = plan.title,
            type = plan.type,
            description = plan.description,
            whenExecute = whenExecute,
            completedType = 0,
            date = mycal.time.time
        )
    }
}