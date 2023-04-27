package com.example.fishfarmapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fishfarmapplication.ui.main.fragments.PageType

class MainViewModel : ViewModel() {
    private var _fragmentStatus = MutableLiveData<PageType>()
    val fragmentStatus : LiveData<PageType> get() = _fragmentStatus
    init {
        _fragmentStatus.value = PageType.Home
    }

    fun updageFragmentStatus(pageType: PageType){
        _fragmentStatus.value = pageType
    }

}
