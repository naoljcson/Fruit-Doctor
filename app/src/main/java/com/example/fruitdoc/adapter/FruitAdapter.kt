package com.example.fruitdoc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitdoc.R
import com.example.fruitdoc.model.Fruit
import com.example.fruitdoc.util.PreferenceHelper


class FruitAdapter(
    private val dataSet: List<Fruit>,
    private val listener: OnRecyclerViewItemClickListener
) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView
        val primaryTitle: TextView

        init {
            // Define click listener for the ViewHolder's View.
            fruitImage = view.findViewById(R.id.fruitImage)
            primaryTitle = view.findViewById(R.id.primaryTitle)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fruit_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val context = viewHolder.itemView.context
        val language = PreferenceHelper(context).language
        var vegetableName = dataSet[position].name
        if (language == "am")
            vegetableName = dataSet[position].amharicName
        viewHolder.primaryTitle.text = vegetableName
        viewHolder.itemView.setOnClickListener { listener.onClick(position) }


        val packageName = context.packageName
        val resId =
            context.resources.getIdentifier(dataSet[position].imageName, "drawable", packageName)
        viewHolder.fruitImage.setImageResource(resId)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnRecyclerViewItemClickListener {
        fun onClick(index: Int)
    }

}
