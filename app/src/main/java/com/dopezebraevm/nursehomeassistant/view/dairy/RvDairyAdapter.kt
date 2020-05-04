package com.dopezebraevm.nursehomeassistant.view.dairy

import android.provider.Settings.System.DATE_FORMAT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dopezebraevm.nursehomeassistant.R
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.item_dairy.view.*
import java.text.SimpleDateFormat

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class RvDairyAdapter : RecyclerView.Adapter<RvDairyAdapter.DairyVH>() {

    private val dairies: MutableList<DairyVO> = ArrayList()
    private var selected: DairyVO? = null
    lateinit var fragment: DairyFragment

    fun setDairyList(dairies: List<DairyVO>) {
        this.dairies.clear()
        this.dairies.addAll(dairies)
        notifyDataSetChanged()
    }

    fun selectDairy(dairy: DairyVO) {
        val isCurrent = dairy == selected
        val lastSelectablePosition = dairies.indexOfFirst { it == selected }
        selected = null
        notifyItemChanged(lastSelectablePosition)
        if (isCurrent) return
        val newPosition = dairies.indexOfFirst { it == dairy }
        if (newPosition == -1) return
        selected = dairy
        notifyItemChanged(newPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DairyVH.newInstance(parent, fragment)

    override fun onBindViewHolder(holder: DairyVH, position: Int) {
        holder.onBind(
            dairies[position],
            dairies[position] == selected,
            position == dairies.lastIndex
        )
    }

    override fun getItemCount(): Int = dairies.size

    class DairyVH(
        itemView: View,
        private val fragment: DairyFragment
    ) : RecyclerView.ViewHolder(itemView) {

        companion object {

            fun newInstance(parent: ViewGroup, fragment: DairyFragment): DairyVH {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_dairy, parent, false)
                return DairyVH(view, fragment)
            }
        }

        fun onBind(vo: DairyVO, isSelected: Boolean, isLast: Boolean) {
            itemView.tv_title.text = vo.title
            itemView.tv_middle.text = "Среднее:\n${vo.middle}"
            itemView.tv_three.text = "Последние:\n${vo.last.subList(0, 3).joinToString("\n")}"
            if (isSelected) {
                itemView.tv_collapse.visibility = View.VISIBLE
                itemView.tv_expand.visibility = View.GONE
                itemView.chart.visibility = View.VISIBLE
                setupChart(vo)
            } else {
                itemView.tv_collapse.visibility = View.GONE
                itemView.tv_expand.visibility = View.VISIBLE
                itemView.chart.visibility = View.GONE
            }
            itemView.end.visibility = if (isLast) View.VISIBLE else View.GONE
            itemView.tv_collapse.setOnClickListener { fragment.selectDairy(vo) }
            itemView.tv_expand.setOnClickListener { fragment.selectDairy(vo) }
        }

        private fun setupChart(vo: DairyVO) {
            settingChart(vo.points)
            addDataForGraph(vo.points)
        }

        private fun addDataForGraph(points: List<Point>) {
            val dataSet = LineDataSet(points.mapIndexed { index, point ->
                Entry(index.toFloat(), point.point.toFloat())
            }, "")
            initGradient(dataSet)
            itemView.chart.data = LineData(dataSet)
            itemView.chart.data.isHighlightEnabled = false
            itemView.chart.invalidate()
        }

        private fun settingChart(points: List<Point>) {
            itemView.chart.axisRight.isEnabled = false
            itemView.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            itemView.chart.xAxis.setDrawGridLines(false)
            itemView.chart.axisLeft.setDrawGridLines(false)
            itemView.chart.axisRight.setDrawGridLines(false)
            itemView.chart.xAxis.valueFormatter
            itemView.chart.xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return setDateFromXAxis(points, value)
                }
            }
            itemView.chart.xAxis.labelRotationAngle = -80f
            itemView.chart.legend.isEnabled = false
            itemView.chart.description = null
        }

        private fun initGradient(dataSet: LineDataSet) {
            dataSet.setDrawFilled(true)
            dataSet.fillDrawable =
                ContextCompat.getDrawable(itemView.context, R.drawable.chart_gradient)
        }

        fun setDateFromXAxis(points: List<Point>, fl: Float) : String {
            val weight = points[fl.toInt()]
            val sdf = SimpleDateFormat("dd MMM ")
            return sdf.format(weight.date)
        }
    }
}