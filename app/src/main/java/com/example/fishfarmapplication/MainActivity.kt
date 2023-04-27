package com.example.fishfarmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.fishfarmapplication.databinding.ActivityMainBinding
import com.example.fishfarmapplication.ui.main.MainViewModel
import com.example.fishfarmapplication.ui.main.fragments.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


private const val TAG_HOME = "home_fragment"
private const val TAG_GRAPH = "graph_fragment"
private const val TAG_LED = "led_fragment"
private const val TAG_MEDICINE = "medicine_fragment"
private const val TAG_MYPAGE = "mypage_fragment"

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val HomeFragment by lazy { HomeFragment() }
    private val LedFragment by lazy { LedFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        hideActionBar()

        viewModel.fragmentStatus.observe(this, Observer {
            when(it){
                PageType.Home->{
                    loadFragment(HomeFragment())
                }
                PageType.Led->{
                    loadFragment(LedFragment())
                }
                PageType.Graph->{
                    loadFragment(GraphFragment())
                }
                PageType.Medicine->{
                    loadFragment(MedicineFragment())
                }
                PageType.Mypage->{
                    loadFragment(MypageFragment())
                }
            }
        })
        setNavigationItemClickListener()


//        initNavigationBar()

//        setFragment(TAG_HOME,HomeFragment())
//
//        binding.navigationView.setOnItemSelectedListener { item->
//            when(item.itemId){
//                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
//                R.id.graphFragment -> setFragment(TAG_GRAPH,GraphFragment())
//                R.id.LedFragment -> setFragment(TAG_LED, LedFragment())
//                R.id.medicineFragment -> setFragment(TAG_MEDICINE, MedicineFragment())
//                R.id.mypageFragment -> setFragment(TAG_MYPAGE, MypageFragment())
//            }
//            true
//        }
    }

    private fun setNavigationItemClickListener(){

        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_homeFragment -> {
                    viewModel.updageFragmentStatus(PageType.Home)
                    return@setOnItemSelectedListener true
                }
                R.id.navi_LedFragment -> {
                    viewModel.updageFragmentStatus(PageType.Led)
                    return@setOnItemSelectedListener true
                }
                R.id.navi_graphFragment->{
                    viewModel.updageFragmentStatus(PageType.Graph)
                    return@setOnItemSelectedListener true
                }
                R.id.navi_medicineFragment->{
                    viewModel.updageFragmentStatus(PageType.Medicine)
                    return@setOnItemSelectedListener true
                }
                R.id.navi_mypageFragment->{
                    viewModel.updageFragmentStatus(PageType.Mypage)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun hideActionBar(){
        val actionbar = supportActionBar
        actionbar?.hide()
    }

}
