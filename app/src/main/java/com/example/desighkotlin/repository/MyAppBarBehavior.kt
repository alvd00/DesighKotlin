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
    ):Boolean{
      val result = dependency is NestedScrollView
      return result
    }


    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        (child as TextView).textSize  = 100f
        child.requestLayout()
        return false
        return super.onStartNestedScroll(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }

    /*/override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val nestedScrollView = dependency as NestedScrollView
        (child as TextView).textSize  = 100f
        child.requestLayout()
        return false
    }*/
}