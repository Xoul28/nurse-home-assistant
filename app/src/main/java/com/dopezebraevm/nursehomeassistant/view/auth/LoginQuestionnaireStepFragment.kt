package com.dopezebraevm.nursehomeassistant.view.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dopezebraevm.nursehomeassistant.MainActivity
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.LocaleResolver
import kotlinx.android.synthetic.main.fragment_questionnaire.*

class LoginQuestionnaireStepFragment : Fragment(R.layout.fragment_questionnaire) {

    private var questionVO: List<QuestionVO> = emptyList()
    private lateinit var adapter: PagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = LocaleResolver.getQuestionnaireList()

        questionVO = json.map { QuestionVO(it.question, it.answers, "") }


        btn_back.visibility = View.GONE
        tv_help_info.visibility = View.GONE

        this.adapter = PagerAdapter {
            questionVO[vp_question.currentItem].answer = it

            if (vp_question.currentItem + 1 == 0) {
                ll_description.visibility = View.VISIBLE
                btn_back.visibility = View.GONE
                tv_help_info.visibility = View.GONE
            } else {
                ll_description.visibility = View.GONE
                btn_back.visibility = View.VISIBLE
                tv_help_info.visibility = View.VISIBLE
            }

            if (vp_question.currentItem != questionVO.size - 1)
                vp_question.setCurrentItem(vp_question.currentItem + 1, true)
            else (activity as MainActivity).showFragment(LoginThirdStepFragment())
        }

        vp_question.adapter = adapter
        adapter.setupData(questionVO)
        adapter.notifyDataSetChanged()

        btn_back.setOnClickListener {
            if (vp_question.currentItem - 1 == 0) {
                ll_description.visibility = View.VISIBLE
                btn_back.visibility = View.GONE
                tv_help_info.visibility = View.GONE
            } else {
                ll_description.visibility = View.GONE
                btn_back.visibility = View.VISIBLE
                tv_help_info.visibility = View.VISIBLE
            }
            vp_question.setCurrentItem(vp_question.currentItem - 1, true)
        }

        vp_question.isUserInputEnabled = false
    }
}