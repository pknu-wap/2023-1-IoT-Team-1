package com.example.fishfarmapplication.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var num = 0

    fun plus() {
        num++
    }

    fun getNum() : Int{
        return num
    }
    // TODO: Implement the ViewModel
}