package com.example.fishfarmapplication.ui.main.recyclerviews

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R

class LedColorSelectListAdapter (val itemList: ArrayList<LedColorSelectListItem>)
    : RecyclerView.Adapter<LedColorSelectListAdapter.LedColorSelectListViewHolder>(){
        inner class LedColorSelectListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val button: Button = itemView.findViewById<Button>(R.id.ledColorSelectButton)
            fun bind(position: Int){
                button.setBackgroundResource(itemList[position].color)
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LedColorSelectListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_led_recycler_view,parent,false)
        return LedColorSelectListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: LedColorSelectListViewHolder, position: Int) {
        holder.bind(position)
    }
}

data class LedColorSelectListItem(val color : Int);
