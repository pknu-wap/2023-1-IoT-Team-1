package com.example.fishfarmapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.fishfarmapplication.databinding.ActivityLoginBinding
import com.example.fishfarmapplication.ui.main.models.User
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    val database = Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val usersRef = database.getReference("users")

    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginImageView.setOnClickListener { signin() }
    }

    fun signup() {
        with(binding) {
            val id = editId.text.toString()
            val password = editPassword.text.toString()

            if(id.isNotEmpty() && password.isNotEmpty()) {
                usersRef.child(id).get().addOnSuccessListener {
                    if(it.exists()) {
                        Toast.makeText(this@LoginActivity,
                            "아이디가 존재합니다.",
                            Toast.LENGTH_LONG)
                            .show()
                    } else {
                        val user = User(id, password)
                        usersRef.child(id).setValue(user)
                        signin()
                    }
                }
            } else {
                Toast.makeText(this@LoginActivity,
                    "아이디, 비밀번호를 모두 입력해야 합니다.",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun signin() {
        with(binding) {
            val id = editId.text.toString()
            val password = editPassword.text.toString()

            if(id.isNotEmpty() && password.isNotEmpty()) {
                usersRef.child(id).get().addOnSuccessListener {
                    if(it.exists()) {
                        it.getValue(User::class.java)?.let { user ->
                            if(user.password == password) {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@LoginActivity, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "아이디가 없습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this@LoginActivity,
                    "아이디, 비밀번호를  입력해야 합니다.",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}