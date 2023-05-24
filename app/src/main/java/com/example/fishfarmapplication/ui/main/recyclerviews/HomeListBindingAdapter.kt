package com.example.fishfarmapplication.ui.main.recyclerviews

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object HomeListBindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items : ArrayList<HomeListItem>, id : String){
        if(recyclerView.adapter == null)
            recyclerView.adapter = HomeListAdapter(id)

        val adapter = recyclerView.adapter as HomeListAdapter

        adapter.itemList = items
        adapter.notifyDataSetChanged()
    }
}