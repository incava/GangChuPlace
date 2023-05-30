package com.incava.gangchuplace.adapter

import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.MyReviewInfo
import com.incava.gangchuplace.viewmodel.FriendViewModel
import com.incava.gangchuplace.viewmodel.GangChuViewModel
import com.incava.gangchuplace.viewmodel.MyInfoViewModel
import com.incava.gangchuplace.viewmodel.WriteViewModel

object ToolbarBindingAdapter {

    //GangChuViewModel과 gangchu_menu를 연결하는 바인딩어댑터
    @JvmStatic
    @BindingAdapter("setGangChuToolbar")
    fun setGangChuToolbar(view: Toolbar, vm: GangChuViewModel) {
        view.setOnMenuItemClickListener {
            vm.itemClick(view, it)
            true
        }
    }

    //Todo 툴바 링크 전송 관련.
    @JvmStatic
    @BindingAdapter("setReviewPageToolbar")
    fun setReviewPageToolbar(view: Toolbar, myReviewInfo: MyReviewInfo) {
        view.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener {
                Toast.makeText(context, "공유 클릭!", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setWriteToolbar")
    fun setWriteToolbar(view: Toolbar, writeViewModel: WriteViewModel) {
        view.setupWithNavController(view.findNavController())
        view.setOnMenuItemClickListener {
            writeViewModel.finishReview(view)
            true
        }
    }

    @JvmStatic
    @BindingAdapter("setReviseInfoToolbar")
    fun setReviseInfoToolbar(view: Toolbar, myInfoViewModel: MyInfoViewModel) {
        view.setupWithNavController(view.findNavController())
        view.setOnMenuItemClickListener {
            //todo 리스너 구현
            myInfoViewModel.saveInfo(view)
            true
        }
    }

    @JvmStatic
    @BindingAdapter("setRevisePassToolbar")
    fun setRevisePassToolbar(view: Toolbar, myInfoViewModel: MyInfoViewModel) {
        view.setupWithNavController(view.findNavController())
        view.setOnMenuItemClickListener {
            //todo 리스너 구현
            myInfoViewModel.saveInfo(view)
            true
        }
    }

    @JvmStatic
    @BindingAdapter("setSearchToolbar")
    fun setSearchToolbar(view: Toolbar, gangChuViewModel: GangChuViewModel) {
        view.apply {
            setupWithNavController(findNavController())
            Log.i("researchKeyword", gangChuViewModel.researchKeyword)
            title = "${gangChuViewModel.researchKeyword}의 검색 결과"
        }
    }

    @JvmStatic
    @BindingAdapter("setFriendToolbar")
    fun setFriendToolbar(view: Toolbar, friendViewModel: FriendViewModel) {
        view.apply {
            setOnMenuItemClickListener {
                //todo 리스너 구현
                if (it.itemId == R.id.request_friend) {
                    findNavController().navigate(R.id.action_friendFragment_to_requestFriendFragmentDialog)
                }
                true
            }
        }
    }
    @JvmStatic
    @BindingAdapter("setMapToolbar")
    fun setMapToolbar(view: Toolbar, s : String) {
        view.apply {
            setOnMenuItemClickListener {
                //강추로 돌아 가는 버튼을 누를 경우,
                if (it.itemId == R.id.gang_chu) {
                    findNavController().navigate(R.id.action_gangChuMapFragment_to_gangChuFragment)
                }
                true
            }
        }
    }


}