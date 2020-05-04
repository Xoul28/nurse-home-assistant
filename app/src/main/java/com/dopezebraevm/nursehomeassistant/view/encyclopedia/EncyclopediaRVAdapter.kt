package com.dopezebraevm.nursehomeassistant.view.encyclopedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import com.dopezebraevm.nursehomeassistant.data.EncyclopediaJson
import kotlinx.android.synthetic.main.item_articale.view.*

class EncyclopediaRVAdapter(var listener: () -> Unit) :
    RecyclerView.Adapter<EncyclopediaRVAdapter.ViewHolder>() {

    private lateinit var parent: ViewGroup
    private var encyclopediaJson: List<EncyclopediaJson> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(encyclopediaJson: EncyclopediaJson) {
            itemView.iv_logo.setImageResource(encyclopediaJson.getImage(parent.context))
            itemView.title.text = encyclopediaJson.title
            itemView.description.text = encyclopediaJson.description
            itemView.setOnClickListener {
                listener.invoke()
            }
        }
    }

    fun setupData(encyclopediaJson: List<EncyclopediaJson>) {
        this.encyclopediaJson = encyclopediaJson
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {

        this.parent = parent

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_articale, parent, false
            )
        )
    }

    override fun getItemCount() = encyclopediaJson.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(encyclopediaJson[position])


}