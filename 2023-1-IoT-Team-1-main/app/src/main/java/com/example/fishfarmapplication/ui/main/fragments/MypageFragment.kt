package com.example.fishfarmapplication.ui.main.fragments

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentGraphBinding
import com.example.fishfarmapplication.databinding.FragmentMedicineBinding
import com.example.fishfarmapplication.databinding.FragmentMypageBinding
import com.example.fishfarmapplication.ui.main.models.entity.GraphEntity
import com.example.fishfarmapplication.ui.main.viewmodels.GraphDataViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.IdViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel


class MypageFragment : Fragment() {
    private lateinit var binding : FragmentMypageBinding
    private val viewModel: PageViewModel by viewModels()
    private val graphDataViewModel: GraphDataViewModel by viewModels()
    private val idViewModel: IdViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
//        dataViewModel.insertAll(GraphEntity(20230521F,1F,2F,3F))

        binding.Fixinfo.setOnClickListener {
            //
        }
        binding.imageView3.setOnClickListener {
            ImageFixDialog().show(childFragmentManager,ImageFixDialog.TAG)

            // 이미지 클릭시, 행동
        }

        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()

    }
}