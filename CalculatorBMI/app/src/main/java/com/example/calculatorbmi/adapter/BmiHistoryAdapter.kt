package com.example.calculatorbmi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatorbmi.R
import com.example.calculatorbmi.controller.bmicontroller.ResultController
import com.example.calculatorbmi.model.History

class BmiHistoryAdapter(
    private val list: List<History>,
    private val resultController: ResultController
) :
    RecyclerView.Adapter<BmiHistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bmiResult: TextView = view.findViewById(R.id.txt_historyBmi)
        val dateHistory: TextView = view.findViewById(R.id.txt_historyDate)
        val img: CardView = view.findViewById(R.id.cardviewHistory)
        val status: TextView = view.findViewById(R.id.txt_sttHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bmiHistory = list[position]
        holder.bmiResult.text = bmiHistory.bmi.toString()
        holder.dateHistory.text = bmiHistory.date
        val resultInfo = resultController.getColorAndTextForStatus(bmiHistory.bmi)
        holder.img.setCardBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                resultInfo.colorResId
            )
        )
        holder.status.text = holder.itemView.context.getString(resultInfo.textResId)
    }
}