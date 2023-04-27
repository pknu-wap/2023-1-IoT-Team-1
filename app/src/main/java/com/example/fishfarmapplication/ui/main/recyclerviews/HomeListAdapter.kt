package com.example.fishfarmapplication.ui.main.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R

class HomeListAdapter (val itemList: ArrayList<HomeListItem>)
    :RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>(){
    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById<TextView>(R.id.itemDescTitle)
        val DataView : TextView = itemView.findViewById<TextView>(R.id.itemDescData)

        fun bind(position: Int){
            titleView.text= itemList[position].title
            DataView.text=itemList[position].data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_recycler_view,parent,false)
        return HomeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }


}

data class HomeListItem(val title:String, val data:String);