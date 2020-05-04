package com.dopezebraevm.nursehomeassistant.data

object Data {

    fun getDirectoryList() : String {
        return """
            [
                "Инсульт",
                "Травма спинного мозга",
                "Ботулизм",
                "Полиомиелит",
                "Рассеяный склероз",
                "Синдром Гийена — Барре",
                "Деменция",
                "Детский церебральный паралич",
                "Клещевой паралич",
                "Отравление",
                "Болезнь Альцгеймера",
                "Болезнь Паркинсона",
                "Энцефалит",
                "Гидроцефалия",
                "Эпидемический паротит",
                "Послеоперационный уход"
            ]
        """.trimIndent()
    }

    fun getQuestionnaireData() : String {
        return """
            [
              {
                "question": "Вид инсульта",
                "answers": [
                  "Ишемический",
                  "Геморрагический"
                ]
              },
              {
                "question": "Способен ли подопечный передвигаться самостоятельно?",
                "answers": [
                  "Да, полностью",
                  "Да, но ему нужна опора или незначительная помощь",
                  "Нет не способен"
                ]
              },
              {
                "question": "Контролирует ли подопечный мочеиспускание?",
                "answers": [
                  "Да",
                  "Нет"
                ]
              },
              {
                "question": "Больной способен питаться самостоятельно?",
                "answers": [
                  "Да",
                  "Нет"
                ]
              },
              {
                "question": "Какой прогнозируют период восстановления?",
                "answers": [
                  "Больше 3 месяцев",
                  "Больше 6 месяцев",
                  "Больше года"
                ]
              }
            ]
        """.trimIndent()
    }

    fun getTaskTemplates() : String {
        return """
            [
              {
                "action": "Обработать медицинское оборудование",
                "description": "",
                "periodicity": "Каждый день",
                "type": "medkit"
              },
              {
                "action": "Проветрить помещение",
                "description": "Необходимо заранее утеплить подопечного, закрыть поясницу и одеть шапку, чтобы его не продуло.",
                "periodicity": "Утром, Днем и Вечером",
                "type": "wind"
              },
              {
                "action": "Ввести лекарство",
                "description": "Отредактируйте описание выбрав лекарство",
                "periodicity": "Утром и Вечером",
                "type": "medicine"
              },
              {
                "action": "Провести Санацию",
                "description": "",
                "periodicity": "Утром",
                "type": "sanation"
              },
              {
                "action": "Почитать перед сном",
                "description": "",
                "periodicity": "Вечером",
                "type": "reading"
              },
              {
                "action": "Вызвать врача",
                "description": "",
                "periodicity": "Утром",
                "type": "doctor"
              }
            ]
        """.trimIndent()
    }

    fun getEncyclopediaData() : String {
        return """
            [
              {
                "image_name": "bathtub_1",
                "title": "Большая статья про инсульт",
                "description": "Здесь мы собрали всю информацию про причины и последствия инсульта, способы лечения и советы врачей"
              },
              {
                "image_name": "body_1",
                "title": "Позаботьтесь о себе",
                "description": "Ухаживая за другим, важно не забыть о себе. Вам нужны силы, чтобы продолжать заботиться о  близком. Как помочь себе?"
              },
              {
                "image_name": "flowerpot_1",
                "title": "Что делать с болью?",
                "description": "Как определить уровень боли и  какие меры можно принять для ее уменьшения"
              },
              {
                "image_name": "medical_prescription_1",
                "title": "Гигена. Уход за телом",
                "description": "Правильная и  регулярная личная гигиена подопечного, особенно малоподвижного, не только важна — она обязательна"
              },
              {
                "image_name": "single_bed_1",
                "title": "Проблемы со сном",
                "description": "Многие  больные страдают нарушением сна. Причины этого многообразны"
              },
              {
                "image_name": "sleep_1",
                "title": "Организация пространства",
                "description": "Комфорт и активность подопечного во многом зависят от того,как организовано его жизненное пространство"
              },
              {
                "image_name": "yoga_1",
                "title": "Профилактика и лечение пролежней",
                "description": "Одной из самых частых проблем малоподвижных пациентов являются пролежни"
              }
            ]
        """.trimIndent()
    }
}