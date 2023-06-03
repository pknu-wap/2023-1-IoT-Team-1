package com.example.fishfarmapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fishfarmapplication.databinding.ActivityMainBinding
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel
import com.example.fishfarmapplication.ui.main.fragments.*
import com.example.fishfarmapplication.ui.main.models.entity.GraphEntity
import com.example.fishfarmapplication.ui.main.viewmodels.GraphDataViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.IdViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG_HOME = "home_fragment"
private const val TAG_GRAPH = "graph_fragment"
private const val TAG_LED = "led_fragment"
private const val TAG_MEDICINE = "medicine_fragment"
private const val TAG_MYPAGE = "mypage_fragment"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var idval: String
    private val viewModel: PageViewModel by viewModels()
    private val graphDataViewModel: GraphDataViewModel by viewModels()
    private val idViewModel: IdViewModel by viewModels()

    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val myRef = database.getReference("sensor")

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val query: Query = myRef.orderByValue().limitToFirst(1)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val childValue = childSnapshot.getValue(Double::class.java)
                    Log.d("파이어베이스", childValue.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // 에러 처리
            }
        })
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        idval = intent.getStringExtra("id").toString()
        hideActionBar()
        idViewModel.updateIdValue(idval)

        graphDataViewModel.insertAll(GraphEntity(20230521F,3F,1F,2F))
        graphDataViewModel.insertAll(GraphEntity(20230520F,1F,4f,3F))
//        lifecycleScope.launch(Dispatchers.IO){
////            graphDataViewModel.deleteAll()
////            graphDataViewModel.insertAll(GraphEntity(20230518F,2F,3F,4F))
//
//        }


        viewModel.fragmentStatus.observe(this, Observer {
            when (it) {
                PageType.Home -> {
                    loadFragment(HomeFragment())
                }
                PageType.Led -> {
                    loadFragment(LedFragment())
                }
                PageType.Graph -> {
                    loadFragment(GraphFragment())
                }
                PageType.Medicine -> {
                    loadFragment(MedicineFragment())
                }
                PageType.Mypage -> {
                    loadFragment(MypageFragment())
                }
                PageType.AddMedicine->{
                    loadFragment(MedicineAddTimeFragment())
                }
                PageType.EditMedicine->{
                    loadFragment(MedicineEditTimeFragment())
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

    private fun setNavigationItemClickListener() {

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
                R.id.navi_graphFragment -> {
                    viewModel.updageFragmentStatus(PageType.Graph)
                    return@setOnItemSelectedListener true
                }
                R.id.navi_medicineFragment -> {
                    viewModel.updageFragmentStatus(PageType.Medicine)
                    return@setOnItemSelectedListener true
                }
                R.id.navi_mypageFragment -> {
                    viewModel.updageFragmentStatus(PageType.Mypage)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun addFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mainFrameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun hideActionBar() {
        val actionbar = supportActionBar
        actionbar?.hide()
    }


}
