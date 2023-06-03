package com.example.fishfarmapplication.ui.main.recyclerviews

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object HomeListBindingAdapter {
    @BindingAdapter("homeitems")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items : ArrayList<HomeListItem>){
        if(recyclerView.adapter == null)
            recyclerView.adapter = HomeListAdapter()

        val adapter = recyclerView.adapter as HomeListAdapter

        adapter.itemList = items
        adapter.notifyDataSetChanged()
    }
}