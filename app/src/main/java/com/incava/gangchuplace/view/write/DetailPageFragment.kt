package com.incava.gangchuplace.view.write

import android.annotation.SuppressLint
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentDetailPageBinding
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.viewmodel.DetailPageViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker

class DetailPageFragment : BaseFragment<FragmentDetailPageBinding>(R.layout.fragment_detail_page),
    OnMapReadyCallback {

    // 받아온 args를 가져와 storePlace에 Json화.
    val args: DetailPageFragmentArgs by navArgs()
    val gangChuPreview: GangChuPreview by lazy {
        Gson().fromJson(args.gangChuPreview, GangChuPreview::class.java)
    }
    lateinit var naverMap : NaverMap

    // 뷰모델에 생성자가 필요해 factory를 이용한 뷰모델 객체 생성.
    private val detailPageViewModel: DetailPageViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        binding.apply {
            detailPageVM = detailPageViewModel
            Log.i("gangChuPreview",gangChuPreview.toString())
            // storePlace를 전달.
            detailPageViewModel.setGangChuPreview(gangChuPreview)
            // 툴바를 연결
            toolbar.setupWithNavController(findNavController())
            ivMapTransparent.setOnTouchListener { view, motionEvent ->
                val action = motionEvent.action
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        scv.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                    MotionEvent.ACTION_UP -> {
                        scv.requestDisallowInterceptTouchEvent(false)
                        true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        scv.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                    else -> true
                }
            }
            val fm = childFragmentManager
            val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
                ?: MapFragment.newInstance().also {
                    fm.beginTransaction().add(R.id.map, it).commit()
                }
            (mapFragment as MapFragment).getMapAsync(this@DetailPageFragment)
        }
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        settingMark()
    }

    private fun settingMark() {
        val pos = LatLng(gangChuPreview.storePlace.mapx,gangChuPreview.storePlace.mapy)
        val cameraUpdate = CameraUpdate.scrollTo(pos) //카메라 움직임.
            .animate(CameraAnimation.Fly) //애니메이션 추가.
        naverMap.moveCamera(cameraUpdate)
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        val marker = Marker()
        marker.position = pos//마커 위도 경도 넣기.
        marker.captionText = gangChuPreview.storePlace.title  // 앱바의 제목과 일치하므로 넣어줌.
        marker.map = naverMap // 마커 생성.
    }



}


