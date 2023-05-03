package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentLedBinding
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel
import com.example.fishfarmapplication.ui.main.recyclerviews.*
import com.example.fishfarmapplication.ui.main.viewmodels.IdViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LedFragment : Fragment() {

    private var ledState: Int = -1
    private lateinit var id: String
    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private lateinit var ledRef: DatabaseReference
    private lateinit var binding: FragmentLedBinding
    private lateinit var viewModel: PageViewModel
    private val idViewModel: IdViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = idViewModel.getValue().toString()
        ledRef = database.getReference("users").child(id).child("led")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[PageViewModel::class.java]
        binding = FragmentLedBinding.inflate(inflater, container, false)
        val itemList = ArrayList<LedColorSelectListItem>()
        itemList.add(LedColorSelectListItem(R.color.led_select_green))
        itemList.add(LedColorSelectListItem(R.color.led_select_blue))
        itemList.add(LedColorSelectListItem(R.color.led_select_red))

        val itemAdapter = LedColorSelectListAdapter(itemList)
        itemAdapter.notifyDataSetChanged()
        binding.ledColorSelectScroll.adapter = itemAdapter
        binding.btnLed.setOnClickListener {
            setBtnText()
            if (ledState > 0) {
                ledRef.child("ledstate").setValue(true)
            } else {
                ledRef.child("ledstate").setValue(false)
            }
        }
        return binding.root

    }

    fun setBtnText() {
        ledState *= -1
        binding.btnLed.text = if (ledState > 0) {
            "LED OFF"
        } else {
            "LED ON"
        }
    }

}