package com.example.fishfarmapplication.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fishfarmapplication.MainActivity
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentLedBinding
import com.example.fishfarmapplication.databinding.FragmentNewIdBinding
import com.example.fishfarmapplication.ui.main.models.User
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewIdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewIdFragment : Fragment() {

    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val usersRef = database.getReference("users")

    private lateinit var binding: FragmentNewIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewIdBinding.inflate(inflater, container, false)
        binding.btnSignUp.setOnClickListener {
            signup()
        }
        return binding.root
    }

    fun signup() {
        with(binding) {
            val id = editNewId.text.toString()
            val password = editNewPassword.text.toString()

            if (id.isNotEmpty() && password.isNotEmpty()) {
                usersRef.child(id).get().addOnSuccessListener {
                    if (it.exists()) {
                        Toast.makeText(
                            context,
                            "아이디가 존재합니다.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val user = User(id, password)
                        usersRef.child(id).setValue(user)
                        Toast.makeText(
                            context,
                            "회원가입 완료",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(context, MainActivity::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "아이디, 비밀번호를 모두 입력해야 합니다.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}