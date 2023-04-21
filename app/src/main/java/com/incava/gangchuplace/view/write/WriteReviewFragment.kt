package com.incava.gangchuplace.view.write

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentWriteReviewBinding
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.WriteViewModel
import java.io.File


class WriteReviewFragment :
    BaseFragment<FragmentWriteReviewBinding>(R.layout.fragment_write_review) {

    private val writeViewModel: WriteViewModel by lazy {
        ViewModelProvider(requireActivity())[WriteViewModel::class.java]
    }

    override fun init() {
        binding.writeVM = writeViewModel
        binding.writeFragment = this@WriteReviewFragment
    }
    fun uploadImage() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    //이미지를 pick하기위한 Intent
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if (uri != null) {
            writeViewModel.image.postValue(findAbsolutePath(uri))
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    //절대경로로 변경해주는 메서드.
    private fun findAbsolutePath(uri:Uri):String{
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context?.contentResolver?.query(uri, projection, null, null, null)
        val columnIndex: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        return columnIndex?.let { File(cursor.getString(it)).absolutePath } ?:"" // 없으면 "" 반환
    }





}