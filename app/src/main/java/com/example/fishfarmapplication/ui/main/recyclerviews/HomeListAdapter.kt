package com.example.fishfarmapplication.ui.main.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel

class HomeListAdapter (val itemList: ArrayList<HomeListItem>)
    :RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>(){

    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById<TextView>(R.id.itemDescTitle)
        val DataView : TextView = itemView.findViewById<TextView>(R.id.itemDescData)

        fun bind(position: Int){
            titleView.text= itemList[position].title
            DataView.text=itemList[position].data
            if(itemList[position].status){
                titleView.setTextColor(R.color.data_skyblue.toInt())
                DataView.setTextColor(R.color.data_skyblue.toInt())
            } else{
                titleView.setTextColor(R.color.red_bad.toInt())
                DataView.setTextColor(R.color.red_bad.toInt())
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

    fun updateWaterData(float : Float, _status : Boolean) {
        itemList[0].data = float.toString()
        itemList[0].status = _status
    }

    fun updatePhData(float: Float, _status: Boolean){
        itemList[1].data = float.toString()
        itemList[1].status = _status
    }

    fun updateFoodData(float: Float,_status: Boolean){
        itemList[2].data = float.toString()
        itemList[2].status = _status
    }


}

data class HomeListItem(val title:String, var data:String, var status:Boolean);