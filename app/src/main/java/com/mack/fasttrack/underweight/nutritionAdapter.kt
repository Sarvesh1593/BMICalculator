package com.mack.fasttrack.underweight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mack.fasttrack.R

class nutritionAdapter(private val tipsList : List<NutritionModel>) :
RecyclerView.Adapter<nutritionAdapter.TipViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nutritionAdapter.TipViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.neutrition_layout,parent,false)
        return  TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: nutritionAdapter.TipViewHolder, position: Int) {
        val tip = tipsList[position]
        holder.titleTextView.text = tip.title
        holder.descriptionTextView.text = tip.description
    }

    override fun getItemCount(): Int {
        return tipsList.size
    }
    class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }
}