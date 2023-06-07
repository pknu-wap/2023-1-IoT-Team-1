package com.example.fishfarmapplication

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fishfarmapplication.databinding.ActivityLoginBinding
import com.example.fishfarmapplication.databinding.FragmentNewIdBinding
import com.example.fishfarmapplication.ui.main.fragments.NewIdFragment
import com.example.fishfarmapplication.ui.main.models.User
import com.example.fishfarmapplication.ui.main.viewmodels.IdViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val usersRef = database.getReference("users")
    private lateinit var idViewModel: IdViewModel
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginImageView.setOnClickListener { signin() }
        binding.imageView6.setOnClickListener {
            val newIdFragment = NewIdFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.LoginText, newIdFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    fun signin() {
        with(binding) {
            val id = editId.text.toString()
            val password = editPassword.text.toString()
            if (id.isNotEmpty() && password.isNotEmpty()) {
                usersRef.child(id).get().addOnSuccessListener {
                    if (it.exists()) {
                        it.getValue(User::class.java)?.let { user ->
                            if (user.password == password) {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.putExtra("id", id)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@LoginActivity, "비밀번호가 다릅니다.", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "아이디가 없습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(
                    this@LoginActivity,
                    "아이디, 비밀번호를  입력해야 합니다.",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }
}