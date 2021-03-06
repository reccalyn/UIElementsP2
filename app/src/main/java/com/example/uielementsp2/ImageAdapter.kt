package com.example.uielementsp2

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ImageAdapter(val c: Context):BaseAdapter() {
    private val mContext: Context
    init {
        mContext = c
    }

    var thumbImages = arrayOf<Int>(
        R.drawable.ariana,
        R.drawable.grande,
        R.drawable.moira
    )
    override fun getCount(): Int {
        return thumbImages.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(mContext)
        imageView.setLayoutParams(GridView@AbsListView.LayoutParams(300, 300))
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setPadding(8, 8, 8, 8)
        imageView.setImageResource(thumbImages[position])
        return imageView
    }
}