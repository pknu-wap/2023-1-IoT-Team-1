package com.example.fishfarmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fishfarmapplication.databinding.ActivityMainBinding
import com.example.fishfarmapplication.ui.main.fragments.GraphFragment
import com.example.fishfarmapplication.ui.main.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG_HOME = "home_fragment"
        private const val TAG_GRAPH = "graph_fragment"
    }

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_HOME,HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.graphFragment -> setFragment(TAG_GRAPH,GraphFragment())
            }
            true
        }



    }
    private fun setFragment(tag: String, fragment: Fragment){
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout,fragment,tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val graph = manager.findFragmentByTag(TAG_GRAPH)

        if(home != null)
            fragTransaction.hide(home)
        if (graph != null)
            fragTransaction.hide(graph)

        if (tag== TAG_HOME)
            if(home!= null)
                fragTransaction.show(home)
        else if (tag== TAG_GRAPH)
            if (graph!=null)
                fragTransaction.show(graph)

        fragTransaction.commitAllowingStateLoss()
    }
}