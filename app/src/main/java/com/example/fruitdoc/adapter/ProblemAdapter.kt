package com.example.fruitdoc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitdoc.R
import com.example.fruitdoc.model.Problem
import com.example.fruitdoc.util.PreferenceHelper


class ProblemAdapter(private val dataSet: List<Problem>, private val listener: OnRecyclerViewItemClickListener ) :
    RecyclerView.Adapter<ProblemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val primaryText: TextView
         val secondaryText: TextView
        init {
            primaryText = view.findViewById(R.id.primaryText)
            secondaryText = view.findViewById(R.id.secondaryText)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.problem_item, viewGroup, false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder){
            val preference = PreferenceHelper(viewHolder.itemView.context)
            if(preference.language == "am"){
                primaryText.text = dataSet[position].amharicName
                secondaryText.text = dataSet[position].name
            } else {
                primaryText.text = dataSet[position].name
                secondaryText.text = dataSet[position].amharicName
            }
            itemView.setOnClickListener { listener.onClick(position) }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnRecyclerViewItemClickListener {
        fun onClick(index: Int)
    }
}