package com.example.fishfarmapplication.ui.main.recyclerviews

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//리사이클러뷰 아이템 밑에 간격 추가하는 클래스
class HomeListDeco(divHeight:Int) : RecyclerView.ItemDecoration() {
    private var divHeight : Int = divHeight;

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if(parent.getChildAdapterPosition(view)!= parent.adapter!!.itemCount - 1)
            outRect.bottom = divHeight;
    }
}