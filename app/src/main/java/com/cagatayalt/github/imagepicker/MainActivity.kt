package com.cagatayalt.github.imagepicker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.cagatayalt.github.imagepicker.databinding.ActivityMainBinding
import com.github.dhaval2404.imagepicker.ImagePicker




class MainActivity : AppCompatActivity() {
    companion object {

        private const val REQUEST_FROM_CAMERA = 1001

        private const val REQUEST_FROM_GALLERY = 1002
    }
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        _binding = ActivityMainBinding.inflate(layoutInflater)


        /*
          dialogButton1.setOnClickListener {
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture, 0)
            Amplitude.getInstance().logEvent("AndroidTakeAPhotoTapped")
            dismiss()
        }

        dialogButton2.setOnClickListener {
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickPhoto, 1)
            Amplitude.getInstance().logEvent("AndroidChooseFromLibraryTapped")
            dismiss()

        }
         */

        initListeners()



        setContentView(binding.root)


    }



    private fun captureImageUsingCamera() {
        ImagePicker.with(this).cameraOnly().start(REQUEST_FROM_CAMERA)
    }


    private fun pickImageFromGallery() {
        ImagePicker.with(this).galleryOnly().start(REQUEST_FROM_GALLERY)
    }




    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!

            // Use Uri object instead of File to avoid storage permissions
            imgProfile.setImageURI(fileUri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }*/


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!

            when(requestCode) {
                REQUEST_FROM_CAMERA -> {
                    binding.imageView.setImageURI(data.data)
                }
                REQUEST_FROM_GALLERY -> {
                    binding.imageView.setImageURI(data.data)
                }
            }
        }

        else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }


    private fun initListeners() {
        binding.btnPickImage.setOnClickListener {
            pickImageFromGallery()
        }
        binding.btnTakePhoto.setOnClickListener {
            captureImageUsingCamera()
        }

    }



}