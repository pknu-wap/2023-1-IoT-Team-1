package com.example.fishfarmapplication.ui.main.recyclerviews

import android.graphics.Color
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.MainActivity
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel
import com.example.fishfarmapplication.databinding.ItemHomeRecyclerViewBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.logging.Handler

class HomeListAdapter() : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

    var itemList = mutableListOf<HomeListItem>()

    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val database =
//            Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
//        private val idRef = database.getReference("users").child(id)

        val titleView: TextView = itemView.findViewById<TextView>(R.id.itemDescTitle)
        val DataView: TextView = itemView.findViewById<TextView>(R.id.itemDescData)

        fun bind(position: Int) {
            titleView.text = itemList[position].title
            DataView.text = itemList[position].data
            if (itemList[position].status) {
                titleView.setTextColor(Color.parseColor("#74D6EA"))
                DataView.setTextColor(Color.parseColor("#74D6EA"))
            } else {
                titleView.setTextColor(Color.parseColor("#F06C83"))
                DataView.setTextColor(Color.parseColor("#F06C83"))
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
