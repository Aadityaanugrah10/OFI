package com.projectbangkit.originfoodindonesia.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import com.projectbangkit.originfoodindonesia.R

class AdapterSlider(var dataSlider: ArrayList<Int>, var context: Activity?) : PagerAdapter() {

    lateinit var layoutInflater: LayoutInflater


    override fun getCount(): Int {
        return dataSlider.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`

    }

    @NonNull
    // untuk ngatur layout
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_slider, container, false)

        // init gambar
        val imgViewSlider: ImageView
        imgViewSlider = view.findViewById(R.id.img_slider)

        // menampilkan gambar
        imgViewSlider.setImageResource(dataSlider[position])
        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, @NonNull `object`: Any) {
        container.removeView(`object` as View)
    }
}