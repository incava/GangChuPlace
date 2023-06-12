package com.incava.gangchuplace.view.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentGangChuMapBinding
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.viewmodel.GangChuViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker


class GangChuMapFragment : BaseFragment<FragmentGangChuMapBinding>(R.layout.fragment_gang_chu_map),
    OnMapReadyCallback {

    lateinit var naverMap: NaverMap
    val gangChuViewModel by lazy { ViewModelProvider(requireActivity())[GangChuViewModel::class.java] }

    override fun init() {

        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        (mapFragment as MapFragment).getMapAsync(this@GangChuMapFragment)
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        //binding.gangChuVM = gangChuViewModel
        observeMapList()
    }

    fun observeMapList() {
        gangChuViewModel.gangChuList.observe(this) {
            Log.i("gangChu",it.toString())
            moveMyLocation()
            settingMark(gangChuPreviewList = it)

        }
    }

    fun moveMyLocation(){
        val cameraUpdate = CameraUpdate.scrollTo(getLocation()) //카메라 움직임.
            .animate(CameraAnimation.Fly) //애니메이션 추가.
        naverMap.moveCamera(cameraUpdate)
    }
    private fun getLocation() : LatLng {
        //만약 권한을 못 받았다면 서울시청으로 놓고 리턴.
        if (checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            return LatLng(37.541, 126.986)
        }
        //받아왔을 때,
        val manager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?: manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        Log.i("loc","long : ${location?.latitude} lng : ${location?.longitude}")
        return LatLng(location?.latitude ?: 37.541, location?.longitude ?: 126.986)
    }


    private fun settingMark(gangChuPreviewList: MutableList<GangChuPreview>) {
        naverMap.apply {
            for (gangChuPreview in gangChuPreviewList) {
                val pos = LatLng(gangChuPreview.storePlace.mapx, gangChuPreview.storePlace.mapy)
                val marker = Marker()
                marker.position = pos//마커 위도 경도 넣기.
                marker.captionText = gangChuPreview.storePlace.title  // 앱바의 제목과 일치하므로 넣어줌.
                marker.map = this@apply // 마커 생성.
            }
        }
    }

}