package com.example.fishfarmapplication.ui.main.recyclerviews

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel

class HomeListAdapter ()
    :RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>(){

    var itemList = mutableListOf<HomeListItem>()
    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById<TextView>(R.id.itemDescTitle)
        val DataView : TextView = itemView.findViewById<TextView>(R.id.itemDescData)

        fun bind(position: Int){
            titleView.text= itemList[position].title
            DataView.text=itemList[position].data
            if(itemList[position].status){
                titleView.setTextColor(Color.parseColor("#74D6EA"))
                DataView.setTextColor(Color.parseColor("#74D6EA"))
            } else {
                titleView.setTextColor(Color.parseColor("#F06C83"))
                DataView.setTextColor(Color.parseColor("#F06C83"))
            }
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


