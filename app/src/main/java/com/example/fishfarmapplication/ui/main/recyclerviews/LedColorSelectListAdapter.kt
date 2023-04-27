package com.example.fishfarmapplication.ui.main.recyclerviews

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
            val button: Button = itemView.findViewById<Button>(R.id.ledTestView)
            fun bind(position: Int){
                button.text = itemList[position].title
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

data class LedColorSelectListItem(val title:String);

//class HomeListAdapter (val itemList: ArrayList<HomelistItem>)
//    : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>(){
//    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val titleView: TextView = itemView.findViewById<TextView>(R.id.itemDescTitle)
//        val DataView : TextView = itemView.findViewById<TextView>(R.id.itemDescData)
//
//        fun bind(position: Int){
//            titleView.text= itemList[position].title
//            DataView.text=itemList[position].data
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_recycler_view,parent,false)
//        return HomeListViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
//        holder.bind(position)
//    }
//
//    override fun getItemCount(): Int {
//        return itemList.count()
//    }
//
//
//}
//
//data class HomelistItem(val title:String,val data:String);