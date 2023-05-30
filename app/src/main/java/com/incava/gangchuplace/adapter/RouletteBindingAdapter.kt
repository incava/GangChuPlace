package com.incava.gangchuplace.adapter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bluehomestudio.luckywheel.LuckyWheel
import com.bluehomestudio.luckywheel.WheelItem
import com.incava.gangchuplace.R
import com.incava.gangchuplace.util.Common.showDialog
import com.incava.gangchuplace.viewmodel.RouletteViewModel

object RouletteBindingAdapter {

    //색깔은 4가지 색깔로 고정적으로 설정
    private val rouletteColor = listOf(Color.RED,Color.BLUE,Color.GREEN,Color.MAGENTA)
    @JvmStatic
    @BindingAdapter("rouletteSetting")
    fun rouletteSetting(view: LuckyWheel, rouletteViewModel: RouletteViewModel) {
        val list = mutableListOf<WheelItem>()
        for (menu in rouletteViewModel.rouletteList.value!!) {
            list.add(
                WheelItem(
                    rouletteColor[list.size % rouletteColor.size],
                    ContextCompat.getDrawable(view.context, R.drawable.vector_thumb_up)
                        ?.let { setBitmap(it) },
                    menu.menuItem
                )
            )
        }
        view.apply {
            //아이템 삽입
            addWheelItems(list)
            //돌림판이 멈췄을때 반응하는 리스너
            setLuckyWheelReachTheTarget {
                rouletteViewModel.isRotate = false
                showDialog(this.context,"메뉴 결과","${list[rouletteViewModel.point.value?.toInt()!!].text}(으)로 결정되었습니다.\n그 곳으로 가볼까요?")
            }
        }

    }

    @JvmStatic
    @BindingAdapter("setRotateRoulette")
    fun setRotateRoulette(view: LuckyWheel, point : Int){
        if (point >= 0)
            //rotateWheelTo는 index처럼 0부터 시작이 아닌 1부터 시작
            view.rotateWheelTo(point+1)
    }

    //LuckyWheel은 크기도 줘야해서 만들때 크기를 주는 메서드
    fun setBitmap(drawable: Drawable) : Bitmap{
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,drawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0,0, canvas.width,canvas.height)
        drawable.draw(canvas)
        return bitmap
    }




}
