package com.example.fishfarmapplication.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IdViewModel : ViewModel() {
    private val _idValue = MutableLiveData<String>()
    val idValue: LiveData<String> = _idValue

    fun updateIdValue(newValue: String) {
        _idValue.value = newValue
    }

    fun getValue() : String? {
        return idValue.value
    }
}