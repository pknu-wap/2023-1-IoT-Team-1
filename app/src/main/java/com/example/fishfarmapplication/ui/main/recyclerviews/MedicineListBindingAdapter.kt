package com.example.fishfarmapplication.ui.main.recyclerviews

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object MedicineListBindingAdapter {
    @BindingAdapter("medicineitems")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items : ArrayList<MedicineListItem>){
        if(recyclerView.adapter == null)
            recyclerView.adapter = MedicineListAdapter()

        val adapter = recyclerView.adapter as MedicineListAdapter

        adapter.itemList = items
        adapter.notifyDataSetChanged()
    }
}