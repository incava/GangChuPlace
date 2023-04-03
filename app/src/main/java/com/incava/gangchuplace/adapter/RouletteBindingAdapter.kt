package com.incava.gangchuplace.adapter

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluehomestudio.luckywheel.LuckyWheel
import com.bluehomestudio.luckywheel.WheelItem
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.RouletteMenuModel

object RouletteBindingAdapter {
    @JvmStatic
    @BindingAdapter("rouletteItem")
    //리사이클러뷰와 ViewModel을 이어줄 바인딩 어댑터
    fun setBindItem(view: RecyclerView,item : MutableList<RouletteMenuModel>) {
        view.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = RouletteAdapter(item)
        }
    }

    // 사진과 색깔은 고정으로 Text만 바꿔서 add하는 식으로 구현. 추후 랜덤요소로 변환 예정.
    @JvmStatic
    @BindingAdapter("rouletteSetting")
    fun rouletteSetting(view: LuckyWheel, item: LiveData<MutableList<RouletteMenuModel>>) {
        val list = mutableListOf<WheelItem>()
        for (menu in item.value!!) {
            list.add(
                WheelItem(
                    Color.BLUE,
                    ContextCompat.getDrawable(view.context, R.drawable.ic_money)
                        ?.let { setBitmap(it) },
                    menu.menuItem
                )
            )
        }
        view.addWheelItems(list)
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
