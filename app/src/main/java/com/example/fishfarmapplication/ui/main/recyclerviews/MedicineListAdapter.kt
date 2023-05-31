package com.example.fishfarmapplication.ui.main.recyclerviews

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishfarmapplication.R

class MedicineListAdapter() : RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {

    var itemList= mutableListOf<MedicineListItem>()

    inner class MedicineViewHolder(itemView: RecyclerView) : RecyclerView.ViewHolder(itemView){
        val TimeTitleView: TextView = itemView.findViewById<TextView>(R.id.medicineItemTimeTextView)
        val APMTextView: TextView = itemView.findViewById<TextView>(R.id.medicineItemAMPMTextView)
        val medicineNameView: TextView = itemView.findViewById<TextView>(R.id.medicineItemNameTextView)
        val descView: TextView = itemView.findViewById<TextView>(R.id.medicineItemDescTextView)


        fun bind(position: Int){
            TimeTitleView.text = itemList[position].time.toString()
            if(itemList[position].time >= 1200){
                APMTextView.text = "오전"
            }else{
                APMTextView.text = "오후"
            }
//            APMTextView.text = itemList[position]
            medicineNameView.text = itemList[position].name
            descView.text=itemList[position].desc

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        TODO("Not yet implemented")

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}


