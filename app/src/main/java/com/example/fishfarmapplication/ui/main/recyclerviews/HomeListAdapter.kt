package com.example.fishfarmapplication.ui.main.recyclerviews

import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.ItemHomeRecyclerViewBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.logging.Handler

class HomeListAdapter(val itemList: ArrayList<HomeListItem>, val id: String) :
    RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {
    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val foodRef = database.getReference("users").child(id).child("food")

    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById<TextView>(R.id.itemDescTitle)
        val DataView: TextView = itemView.findViewById<TextView>(R.id.itemDescData)
        val button: Button = itemView.findViewById<Button>(R.id.btnTest)

        fun bind(position: Int) {
            titleView.text = itemList[position].title
            DataView.text = itemList[position].data
            button.setOnClickListener {
                foodRef.child("state").setValue(1)

                android.os.Handler(Looper.getMainLooper()).postDelayed({
                    foodRef.child("state").setValue(0)
                }, 1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
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

data class HomeListItem(val title: String, val data: String);