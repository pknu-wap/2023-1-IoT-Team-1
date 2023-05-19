package com.example.fishfarmapplication.ui.main.recyclerviews

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.ItemHomeRecyclerViewBinding

class HomeListAdapter (val itemList: ArrayList<HomeListItem>)
    :RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>(){
    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById<TextView>(R.id.itemDescTitle)
        val DataView : TextView = itemView.findViewById<TextView>(R.id.itemDescData)
        val button: Button = itemView.findViewById<Button>(R.id.btnTest)

        fun bind(position: Int){
            titleView.text= itemList[position].title
            DataView.text=itemList[position].data
            button.setOnClickListener {
                Log.d("테스트", titleView.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_recycler_view,parent,false)
        val binding = ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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