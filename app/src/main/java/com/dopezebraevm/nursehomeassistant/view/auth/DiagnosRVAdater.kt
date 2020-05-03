package com.dopezebraevm.nursehomeassistant.view.auth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.Data
import com.dopezebraevm.nursehomeassistant.data.LocaleResolver
import kotlinx.android.synthetic.main.item_diagnosis.view.*

class DiagnosRVAdater(val callback: (String) -> Unit?) : RecyclerView.Adapter<DiagnosRVAdater.ViewHolder>() {

    var diagnosis: List<String> = emptyList()

    fun setupData(diagnosis: List<String>) {
        this.diagnosis = diagnosis
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            itemView.tv_content.text = item
            itemView.setOnClickListener {
                callback.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_diagnosis, parent, false))

    override fun getItemCount(): Int {
        return diagnosis.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(diagnosis[position])
}