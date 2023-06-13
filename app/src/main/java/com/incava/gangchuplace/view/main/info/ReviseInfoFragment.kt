package com.incava.gangchuplace.view.main.info

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentReviseInfoBinding
import com.incava.gangchuplace.util.Common
import com.incava.gangchuplace.util.ProfileCode
import com.incava.gangchuplace.viewmodel.MyInfoViewModel
import com.incava.gangchuplace.viewmodel.ReviseInfoViewModel
import java.io.File

class ReviseInfoFragment : BaseFragment<FragmentReviseInfoBinding>(R.layout.fragment_revise_info) {
    private val reviseInfoViewModel : ReviseInfoViewModel by viewModels()
    override fun init() {
        binding.reviseInfoVM = reviseInfoViewModel
        binding.reviseInfoFragment = this@ReviseInfoFragment
        initObserve()
    }

    fun uploadImage() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    //이미지를 pick하기위한 Intent
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if (uri != null) {
            reviseInfoViewModel.image.postValue(findAbsolutePath(uri))
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    //절대경로로 변경해주는 메서드.
    private fun findAbsolutePath(uri: Uri):String{
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context?.contentResolver?.query(uri, projection, null, null, null)
        val columnIndex: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        return columnIndex?.let { File(cursor.getString(it)).absolutePath } ?:"" // 없으면 "" 반환
    }

    // 저장 버튼을 누르면 분기처리
    fun initObserve(){
        reviseInfoViewModel.resultEvent.observe(this){
            when(it){
                ProfileCode.UpLoadFail -> {
                    Common.showDialog(requireContext(),"업로드 실패",ProfileCode.UpLoadFail.comment)
                }
                ProfileCode.ExistNickName -> {
                    Common.showDialog(requireContext(),"중복된 닉네임",ProfileCode.ExistNickName.comment)
                }
                ProfileCode.TransformSuccess ->  {
                    Common.showDialog(requireContext(),"저장 완료",ProfileCode.TransformSuccess.comment)
                    findNavController().navigate(R.id.action_reviseInfoFragment_to_baseContainerFragment)
                }
            }
        }
    }

}