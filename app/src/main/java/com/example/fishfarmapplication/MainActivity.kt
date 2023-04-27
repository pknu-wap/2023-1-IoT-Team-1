package com.example.fishfarmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fishfarmapplication.databinding.ActivityMainBinding
import com.example.fishfarmapplication.ui.main.fragments.GraphFragment
import com.example.fishfarmapplication.ui.main.fragments.HomeFragment
import com.example.fishfarmapplication.ui.main.fragments.LedFragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private const val TAG_HOME = "home_fragment"
private const val TAG_GRAPH = "graph_fragment"
private const val TAG_LED = "led_fragment"
private const val TAG_MEDICINE = "medicine_fragment"
private const val TAG_MYPAGE = "mypage_fragment"

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val actionbar = supportActionBar
        actionbar?.hide()

        setFragment(TAG_HOME,HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.graphFragment -> setFragment(TAG_GRAPH,GraphFragment())
                R.id.LedFragment -> setFragment(TAG_LED, LedFragment())
            }
            true
        }



    }
    fun setFragment(tag: String, fragment: Fragment){
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction: FragmentTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout,fragment,tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val graph = manager.findFragmentByTag(TAG_GRAPH)
        val led = manager.findFragmentByTag(TAG_LED)
        val medicine = manager.findFragmentByTag(TAG_MEDICINE)
        val mypage= manager.findFragmentByTag(TAG_MYPAGE)

        home?.let { fragTransaction.hide(it) }
        graph?.let { fragTransaction.hide(it) }
        led?.let { fragTransaction.hide(it) }
        medicine?.let { fragTransaction.hide(it) }
        mypage?.let { fragTransaction.hide(it) }
        
        //현재 페이지 상태를 flag로 저장해놓면 위와 같은 짓을 안해도 되긴함

        when(tag){
            TAG_HOME-> home?.let { fragTransaction.show(it) }
            TAG_GRAPH-> graph?.let { fragTransaction.show(it) }
            TAG_LED->graph?.let { fragTransaction.show(it) }
            TAG_MEDICINE->medicine?.let{fragTransaction.show(it)}
            TAG_MYPAGE->mypage?.let { fragTransaction.show(it) }
        }


        fragTransaction.commitAllowingStateLoss()
    }

}