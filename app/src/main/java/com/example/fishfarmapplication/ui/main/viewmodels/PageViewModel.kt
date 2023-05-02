package com.example.fishfarmapplication.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fishfarmapplication.ui.main.fragments.PageType

//프래그먼트 창 변환을 위한 뷰모델
class PageViewModel : ViewModel() {
    private var _fragmentStatus = MutableLiveData<PageType>()


    val fragmentStatus : LiveData<PageType> get() = _fragmentStatus
    init {
        _fragmentStatus.value = PageType.Home
    }

    fun updageFragmentStatus(pageType: PageType){
        _fragmentStatus.value = pageType
    }

}
