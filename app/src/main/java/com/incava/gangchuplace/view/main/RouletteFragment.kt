package com.incava.gangchuplace.view.main

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bluehomestudio.luckywheel.WheelItem
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRouletteBinding
import kotlin.random.Random

class RouletteFragment : BaseFragment<FragmentRouletteBinding>(R.layout.fragment_roulette) {
    private var wheel = mutableListOf<WheelItem>()
    private var point = 0
    private lateinit var bitmap : Bitmap
    override fun init() {
        var d = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_money)
        bitmap = drawableToBitmap(d!!)
        binding.roulette = this
        wheel.add(WheelItem(Color.parseColor("#F44336"),bitmap,"짜장면"))
        //기본틀이므로 viewbinding만 사용.
        binding.lwv.addWheelItems(wheel)
        binding.lwv.setTarget(0)
        binding.lwv.setLuckyWheelReachTheTarget {
            var wheelItem = wheel[point]
            binding.lwv.isClickable = true
            Toast.makeText(requireContext(),wheelItem.text,Toast.LENGTH_SHORT).show()
        }
    }
    fun playWheel(){
        point = Random.nextInt(wheel.size)
        binding.lwv.apply {
            isClickable = false
            addWheelItems(wheel)
            rotateWheelTo(point)
        }
    }





    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if(drawable is BitmapDrawable){
            return drawable.bitmap
        }
        var bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)
        drawable.setBounds(0,0,canvas.width,canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

}