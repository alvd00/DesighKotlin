package com.example.desighkotlin.repository

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.size
import androidx.core.widget.NestedScrollView
import com.example.desighkotlin.R
import com.google.android.material.bottomappbar.BottomAppBar

class MyAppBarBehavior @JvmOverloads constructor(
    context: Context? = null, attrs : AttributeSet? = null) : CoordinatorLayout.Behavior<View>(context,attrs) {
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    )=dependency is NestedScrollView
//Хотел, чтобы при скролле менялся размер текста
    /*override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val nestedScrollView = dependency as NestedScrollView
        child.textSize = R.dimen.bigTextSize
        child.requestLayout()
        return false
    }*/
}