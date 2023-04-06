package com.example.fishfarmapplication.ui.main

import android.media.metrics.Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val test = 1
    val count = MutableLiveData<Int>().apply { value = 0 }
}
