package com.example.fishfarmapplication.ui.main.recyclerviews

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
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.MainActivity
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.ItemHomeRecyclerViewBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.logging.Handler

class HomeListAdapter(val itemList: ArrayList<HomeListItem>, val id: String) :
    RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {
    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val idRef = database.getReference("users").child(id)

    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById<TextView>(R.id.itemDescTitle)
        val DataView: TextView = itemView.findViewById<TextView>(R.id.itemDescData)
        val button: Button = itemView.findViewById<Button>(R.id.btnTest)

        fun bind(position: Int) {
            titleView.text = itemList[position].title
            DataView.text = itemList[position].data
            button.setOnClickListener {
                val text = EditText(itemView.context)
                text.gravity = Gravity.CENTER
                when (position.toString()) {
                    "0" -> {
                        val builder = AlertDialog.Builder(itemView.context)
                            .setTitle("기준 수온값 설정")
                            .setView(text)
                            .setPositiveButton(
                                "확인",
                                DialogInterface.OnClickListener { dialog, which ->
                                    val tempStandard = text.text.toString().toFloat()
                                    idRef.child("temperature").child("standard").setValue(tempStandard)
                                })
                            .setNegativeButton(
                                "취소",
                                DialogInterface.OnClickListener{ dialog, which ->
                                }
                            )
                        builder.show()
                    }
                    "1" -> {
                        val builder = AlertDialog.Builder(itemView.context)
                            .setTitle("기준 pH값 설정")
                            .setView(text)
                            .setPositiveButton(
                                "확인",
                                DialogInterface.OnClickListener { dialog, which ->
                                    val phStandard = text.text.toString().toFloat()
                                    idRef.child("ph").child("standard").setValue(phStandard)
                                })
                            .setNegativeButton(
                                "취소",
                                DialogInterface.OnClickListener{ dialog, which ->
                                }
                            )
                        builder.show()
                    }
                    "2" -> {
                        val builder = AlertDialog.Builder(itemView.context)
                            .setTitle("먹이 공급 시간 설정")
                            .setView(text)
                            .setPositiveButton(
                                "확인",
                                DialogInterface.OnClickListener { dialog, which ->
                                    val feedStandard = text.text.toString().toFloat()
                                    idRef.child("feed").child("standard").setValue(feedStandard)
                                })
                            .setNegativeButton(
                                "취소",
                                DialogInterface.OnClickListener{ dialog, which ->
                                }
                            )
                        builder.show()
                    }
                }
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