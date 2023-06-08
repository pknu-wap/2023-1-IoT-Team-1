package com.example.fishfarmapplication.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date

class HomeViewModel : ViewModel(){
    //true면 상태 좋음 false 상태 나쁨
    private var _homeStatus = MutableLiveData<Boolean>()

    val homeStatus : LiveData<Boolean> get() = _homeStatus

    private var _waterTemperatureStandard = MutableLiveData<Float>()
    private var _phStandard = MutableLiveData<Float>()
    private var _foodStandard = MutableLiveData<Float>()

    val waterTemperatureStandard : LiveData<Float> get() = _waterTemperatureStandard
    val phStandard : LiveData<Float> get() = _phStandard
    val foodStandard : LiveData<Float> get() = _foodStandard

    private var _waterTemperatureData =MutableLiveData<Float>()
    private var _phData = MutableLiveData<Float>()
    private var _foodData = MutableLiveData<Float>()

    val waterTemperatureData : LiveData<Float> get() = _waterTemperatureData
    val phData : LiveData<Float> get() = _phData
    val foodData : LiveData<Float> get() = _foodData

    private var _waterTemperatureStatus = MutableLiveData<Boolean>()
    private var _phStatus = MutableLiveData<Boolean>()
    private var _foodStatus = MutableLiveData<Boolean>()

    val waterTemperatureStatus :LiveData<Boolean> get() = _waterTemperatureStatus
    val phStatus : LiveData<Boolean> get() = _phStatus
    val foodStatus : LiveData<Boolean> get() = _foodStatus

    private val _homeItemList = MutableLiveData<ArrayList<HomeListItem>>()

    val homeItemList : LiveData<ArrayList<HomeListItem>> get() = _homeItemList

    private var homeItems : ArrayList<HomeListItem>

    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val usersRef = database.getReference("users").child("arduino").child("sensors")

    companion object{
        val yearStringFormatter = SimpleDateFormat("yyyy년MM월dd일")
        val hourStringFormatter = SimpleDateFormat("HH:mm")
        val timeStringFormatter = SimpleDateFormat("yyyy-MM-dd H:mm:ss")
        val aaStringFormat = SimpleDateFormat("aa")
    }
    init {
        _homeStatus.value = true

        _waterTemperatureStandard.value = 10F
        _phStandard.value = 10F
        _foodStandard.value = 4F

        usersRef.child("water").limitToLast(1).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val childValue = childSnapshot.getValue(Float::class.java)
                    _waterTemperatureData.value = childValue
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        usersRef.child("pH").limitToLast(1).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val childValue = childSnapshot.getValue(Float::class.java)
                    _phData.value = childValue
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        usersRef.child("feed").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val foodValue = dataSnapshot.getValue(Float::class.java)
                _foodData.value = foodValue
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        _waterTemperatureStatus.value = if(waterTemperatureStandard.value == waterTemperatureData.value) true else false
        _phStatus.value = if(phStandard.value == phData.value) true else false
        _foodStatus.value = if(foodStandard.value == foodData.value) true else false

        homeItems = arrayListOf(
            HomeListItem("수온",waterTemperatureData.value.toString(),waterTemperatureStatus.value!!,
                R.drawable.ic_action_water_drop),
            HomeListItem("PH", phData.value.toString(), phStatus.value!!,R.drawable.ic_action_ph),
            HomeListItem("먹이",foodData.value.toString(), foodStatus.value!!,R.drawable.ic_action_food)
        )
        _homeItemList.value = homeItems



//        _waterTemperatureStatus.value = if(standard.value.waterTemperature == waterTemperatureData.value) true else false
//        _foodStatus.value = if(foodStandard.value == foodData.value) true else false
//        _phStatus.value = if(phStandard.value == phData.value) true else false



    }
    fun setHomeStatusValue(boolean: Boolean){
        _homeStatus.value = boolean
    }

    fun setWaterTemperatureStandard(float : Float){
        _waterTemperatureStandard.value = float
    }

    fun setPhStandard(float: Float){
        _phStandard.value = float
//        _phStandard.value = float
    }

    fun setFoodStandard(float: Float){
        _foodStandard.value = float
    }

    fun setWaterTemperatureData(float: Float){
        _waterTemperatureData.value = float
    }

    fun setPhData(float:Float){
        _phData.value = float
    }

    fun setFoodData(float: Float){
        _foodData.value = float
    }

    fun setWaterTemperatureStatus(boolean: Boolean){
        _waterTemperatureStatus.value = boolean
    }

    fun setPhStatus(boolean: Boolean){
        _phStatus.value = boolean
    }

    fun setFoodStatus(boolean: Boolean){
        _foodStatus.value = boolean
    }

    fun updateHomeList() {
        val list = arrayListOf<HomeListItem>(
            HomeListItem("수온",waterTemperatureData.value.toString(),waterTemperatureStatus.value!!,
                R.drawable.ic_action_water_drop),
            HomeListItem("PH", phData.value.toString(), phStatus.value!!,R.drawable.ic_action_ph),
            HomeListItem("먹이",foodData.value.toString(), foodStatus.value!!,R.drawable.ic_action_food)
        )
        _homeItemList.value = list
    }

    fun checkData(){
        if(waterTemperatureData.value != waterTemperatureStandard.value){
            _waterTemperatureStatus.value = false
        } else
            _waterTemperatureStatus.value = true

        if(phData.value != phStandard.value){
            _phStatus.value = false
        }else
            _phStatus.value = true

        if(foodData.value != foodStandard.value)
            _foodStatus.value = false
        else
            _foodStatus.value = true

        if(waterTemperatureStatus.value!! && phStatus.value!!
            && foodStatus.value!!)
            _homeStatus.value = true
        else
            _homeStatus.value = false

        updateHomeList()
    }

    fun displayFoodData(){
        val milliseSeconds = System.currentTimeMillis()
        val second = milliseSeconds!!.div(1000)
        val min = milliseSeconds!!.div(1000).div(60)
        val hour = milliseSeconds!!.div(1000).div(60).div(60)
        val day = hour.div(24)
        Log.d("HomeViewModel)", "day : " + day)
    }





}