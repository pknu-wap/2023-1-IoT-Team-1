package com.example.fishfarmapplication.ui.main.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.fishfarmapplication.Manifest
import com.example.fishfarmapplication.databinding.FragmentDialogMedicineCenterBinding
import com.example.fishfarmapplication.databinding.FragmentImageFixDialogBinding
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel
import java.io.File

class ImageFixDialog : DialogFragment() {
    private var _binding: FragmentImageFixDialogBinding? = null
    var photoUri:Uri ?= null

    private val binding get() = _binding!!

    lateinit var cameraPermission: ActivityResultLauncher<String>
    lateinit var storagePermission: ActivityResultLauncher<String>

    lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    lateinit var gallaryLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storagePermission = registerForActivityResult(ActivityResultContracts.RequestPermission())
        { isGranted->
            if(isGranted) {
                setViews()
            }
            else{
                Toast.makeText(this.context, "외부 저장소 권한을 승인해야합니다.", Toast.LENGTH_LONG).show()
            }
        }

        cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission())
        { isGranted->
            if(isGranted) {
                openCamera()
            }
            else{
                Toast.makeText(this.context, "카메라 권한을 승인해야합니다.", Toast.LENGTH_LONG).show()
            }
        }

        gallaryLauncher = registerForActivityResult(ActivityResultContracts.GetContent())
        {
            uri ->
            binding.imageView2.setImageURI(uri)
        }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()){
            isSuccess->
            binding.imageView2.setImageURI(photoUri)
        }

        storagePermission.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

//        _binding = FragmentDialogHomeCenterBinding.inflate(LayoutInflater.from(context))
        _binding = FragmentImageFixDialogBinding.inflate(LayoutInflater.from(context))

        val cameraBtn = binding.cameraon
        val gallaryBtn = binding.gallary
        val backBtn = binding.back
        cameraBtn.setOnClickListener{
            dismiss()
        }
        gallaryBtn.setOnClickListener{
            openGallary()
        }
        backBtn.setOnClickListener{
            onDestroyView()
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    fun setViews(){
        binding.cameraon.setOnClickListener{
            cameraPermission.launch(android.Manifest.permission.CAMERA)
        }
        binding.gallary.setOnClickListener {
            openGallary()
        }
    }

    fun openGallary() {
        gallaryLauncher.launch("image/*")
    }

    fun openCamera() {
        val photoFile = File.createTempFile(
            "IMG_",
            ".jpg",
            context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        photoUri = FileProvider.getUriForFile(requireContext(),"com.example.fishfarmapplication.provider", photoFile)
        cameraLauncher.launch(photoUri)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        const val TAG = "ImageFixDialog"
    }
}