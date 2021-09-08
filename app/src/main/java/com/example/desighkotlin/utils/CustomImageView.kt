package com.example.desighkotlin.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import java.util.jar.Attributes

class CustomImageView @JvmOverloads constructor(context : Context, attributes : AttributeSet? = null, defStyleAttributes: Int = 0) : AppCompatImageView(context, attributes, defStyleAttributes) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}