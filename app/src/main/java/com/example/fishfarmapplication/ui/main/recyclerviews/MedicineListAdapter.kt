package com.example.fishfarmapplication.ui.main.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.ItemHomeRecyclerViewBinding

class MedicineListAdapter() : RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {

    var itemList= mutableListOf<MedicineListItem>()

    inner class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val TimeTitleView: TextView = itemView.findViewById<TextView>(R.id.medicineItemTimeTextView)
        val APMTextView: TextView = itemView.findViewById<TextView>(R.id.medicineItemAMPMTextView)
        val medicineNameView: TextView = itemView.findViewById<TextView>(R.id.medicineItemNameTextView)
        val descView: TextView = itemView.findViewById<TextView>(R.id.medicineItemDescTextView)

        fun bind(position: Int){
            TimeTitleView.text = itemList[position].time
//            if(itemList[position].time >= 1200){
//                APMTextView.text = "오전"
//            }else{
//                APMTextView.text = "오후"
//            }
//            APMTextView.text = itemList[position]
            medicineNameView.text = itemList[position].name
            descView.text=itemList[position].desc

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineListAdapter.MedicineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicine_recycler_view, parent, false)
        return MedicineViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(position)
    }
}


