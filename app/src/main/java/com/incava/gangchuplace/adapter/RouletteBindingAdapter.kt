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
    fun setBindItem(view: RecyclerView, item: LiveData<MutableList<RouletteMenuModel>>) {
        if (view.adapter == null) { // 처음에는 어댑터가 연결되어있지 않다.
            Log.i("bindingNull", "null이동경로 확인")
            val lm = LinearLayoutManager(view.context)
            val adapter = RouletteAdapter()
            view.layoutManager = lm
            view.adapter = adapter
        }
        //널일리가 없지만 널이아니라면 실행하도록.
        view.adapter?.run {
            if (this is RouletteAdapter) {
                item.value?.let { this.rouletteArray = it } ?: run {
                    rouletteArray = arrayListOf()
                }
                Log.i("data", rouletteArray.toString())
                this.notifyDataSetChanged()
            }
        }
    }
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
    fun setBitmap(drawable: Drawable) : Bitmap{
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,drawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0,0, canvas.width,canvas.height)
        drawable.draw(canvas)
        return bitmap
    }


}
