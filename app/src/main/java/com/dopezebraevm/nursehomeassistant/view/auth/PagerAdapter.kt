package com.dopezebraevm.nursehomeassistant.view.auth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.item_question.view.*

class PagerAdapter(val listener: (String) -> Unit?) : RecyclerView.Adapter<PagerAdapter.ViewHolder>() {

    private var questionVO: List<QuestionVO> = emptyList()

    inner class ViewHolder(itemView: View, val listener: (String) -> Unit?) : RecyclerView.ViewHolder(itemView) {

        fun onBind(questionVO: QuestionVO) {
            itemView.tv_question.text = questionVO.question
            itemView.rv_variant_answer.adapter = VariantAsnwerRVAdapter(questionVO.answers) {
                listener.invoke(it)
            }
        }
    }

    fun setupData(questionVO: List<QuestionVO>) {
        this.questionVO = questionVO
    }

    fun onCallback(answer: String) {
        listener.invoke(answer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)) { onCallback(it) }

    override fun getItemCount() = questionVO.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(questionVO[position])
}