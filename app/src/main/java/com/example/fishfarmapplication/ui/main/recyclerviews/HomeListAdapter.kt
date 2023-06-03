package com.example.fishfarmapplication.ui.main.recyclerviews

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.ItemHomeRecyclerViewBinding

class HomeListAdapter() : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

    var itemList = mutableListOf<HomeListItem>()

    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val database =
//            Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
//        private val idRef = database.getReference("users").child(id)

        val titleView= itemView.findViewById<TextView>(R.id.homeItemDescTitle)
        val dataView= itemView.findViewById<TextView>(R.id.homeItemDescData)
        val iconView = itemView.findViewById<ImageView>(R.id.homeItemIcon)

        fun bind(position: Int) {
            titleView.text = itemList[position].title
            dataView.text = itemList[position].data
            iconView.setImageResource(itemList[position].icon)
            if (itemList[position].status) {
                titleView.setTextColor(Color.parseColor("#74D6EA"))
                dataView.setTextColor(Color.parseColor("#74D6EA"))
            } else {
                titleView.setTextColor(Color.parseColor("#F06C83"))
                dataView.setTextColor(Color.parseColor("#F06C83"))
            }
        }
    }

//    inner class HomeListViewHolder(binding: ItemHomeRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
////        private val binding: ItemHomeRecyclerViewBinding = binding
////        fun bind(position: Int) {
////            with(binding){
////                homeItemDescTitle.text = itemList[position].title
////                homeItemDescData.text = itemList[position].data
////                homeItemIcon.setImageResource(itemList[position].icon)
////                if (itemList[position].status) {
////                    homeItemDescTitle.setTextColor(Color.parseColor("#74D6EA"))
////                    homeItemDescData.setTextColor(Color.parseColor("#74D6EA"))
////                } else {
////                    homeItemDescTitle.setTextColor(Color.parseColor("#F06C83"))
////                    homeItemDescData.setTextColor(Color.parseColor("#F06C83"))
////                }
////            }
////
////        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListAdapter.HomeListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_recycler_view, parent, false)
        val binding =
            ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }
}


