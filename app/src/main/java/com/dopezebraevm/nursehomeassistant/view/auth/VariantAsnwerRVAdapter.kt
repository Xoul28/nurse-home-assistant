package com.dopezebraevm.nursehomeassistant.view.auth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import kotlinx.android.synthetic.main.item_questionnaire_variant_answer.view.*
import java.util.zip.Inflater

class VariantAsnwerRVAdapter(val answers: List<String>, val listener: (String) -> Unit?) : RecyclerView.Adapter<VariantAsnwerRVAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(title: String) {
            itemView.btn_answer.text = title
            itemView.btn_answer.setOnClickListener {
                listener.invoke(title)
            }
        }
    }

    override fun getItemCount() = answers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(answers[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_questionnaire_variant_answer, parent, false))
}