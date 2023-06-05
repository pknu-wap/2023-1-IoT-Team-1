package com.example.fishfarmapplication.ui.main.recyclerviews

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.ui.main.viewmodels.IdViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LedColorSelectListAdapter(val itemList: ArrayList<LedColorSelectListItem>, val id: String) :
    RecyclerView.Adapter<LedColorSelectListAdapter.LedColorSelectListViewHolder>() {
    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val ledRef = database.getReference("users").child(id).child("led")

    inner class LedColorSelectListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById<Button>(R.id.ledColorSelectButton)
        fun bind(position: Int) {
            button.setBackgroundResource(itemList[position].color)
            button.setOnClickListener {
                when (position.toString()) {
                    "0" -> {
                        ledRef.child("R").setValue(0)
                        ledRef.child("G").setValue(255)
                        ledRef.child("B").setValue(0)
                    }
                    "1" -> {
                        ledRef.child("R").setValue(0)
                        ledRef.child("G").setValue(0)
                        ledRef.child("B").setValue(255)
                    }
                    "2" -> {
                        ledRef.child("R").setValue(255)
                        ledRef.child("G").setValue(0)
                        ledRef.child("B").setValue(0)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LedColorSelectListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_led_recycler_view, parent, false)
        return LedColorSelectListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: LedColorSelectListViewHolder, position: Int) {
        holder.bind(position)
    }
}

data class LedColorSelectListItem(val color: Int);
