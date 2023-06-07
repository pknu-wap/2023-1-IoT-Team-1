package com.example.fishfarmapplication.ui.main.recyclerviews

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.ItemMedicineRecyclerViewBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter
import kotlin.collections.ArrayList

class MedicineListAdapter(arrayList: ArrayList<MedicineListItem>) : RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {

    var itemList: ArrayList<MedicineListItem>

    init {
        itemList = arrayList
    }


    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
    private lateinit var mOnItemClickListener: OnItemClickListener


    companion object{
        val yearStringFormatter = SimpleDateFormat("yyyy년MM월dd일")
        val hourStringFormatter = SimpleDateFormat("HH:mm")
        val timeStringFormatter = SimpleDateFormat("yyyy-MM-dd H:mm:ss")
        val aaStringFormat = SimpleDateFormat("aa")
    }

    inner class MedicineViewHolder(private val binding:ItemMedicineRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(position: Int){
            val time = timeStringFormatter.parse(itemList[position].time)
//            val calendar = Calendar.getInstance()
//            calendar.time = time
            Log.d("test", time.toString())
            val name = itemList[position].name
            val desc = itemList[position].desc
            with(binding){
                medicineItemYear.text = yearStringFormatter.format(time)
                medicineItemHourTextView.text = hourStringFormatter.format(time)
                medicineItemAMPMTextView.text = aaStringFormat.format(time)
                medicineItemNameTextView.text = name
                medicineItemDescTitleTextView.text = desc
                medicineItem.setOnClickListener {
                    mOnItemClickListener.onItemClick(medicineItem, position)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineListAdapter.MedicineViewHolder {
        val binding = ItemMedicineRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MedicineViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    fun viewClicked(item: MedicineListItem){
        Log.d("clicked?", item.toString())
    }
}


