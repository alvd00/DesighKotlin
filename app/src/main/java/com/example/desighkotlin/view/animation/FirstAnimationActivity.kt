package com.example.desighkotlin.view.animation

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.ActivityAnimationBinding

class FirstAnimationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimationBinding
    private var imageVisible = true
    private var isExpended = false
    var isRigthAnimation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val earth = R.drawable.earth_planet
        val mars = R.drawable.mars_planet
        val venus = R.drawable.venus_planet

        val planetsList = listOf(earth, mars, venus)

        var clickerFlag = 0

        binding.animationButton.setOnClickListener {
            isRigthAnimation = !isRigthAnimation
            val rightChangeBounds = ChangeBounds()
            rightChangeBounds.setPathMotion(ArcMotion())
            rightChangeBounds.duration = 1000
            TransitionManager.beginDelayedTransition(binding.transitionContainer, rightChangeBounds)

            val buttonParams = binding.animationButton.layoutParams as FrameLayout.LayoutParams
            if (isRigthAnimation) {
                buttonParams.gravity = Gravity.END or Gravity.BOTTOM
            } else {
                buttonParams.gravity = Gravity.CENTER or Gravity.CENTER
            }
            binding.animationButton.layoutParams = buttonParams

            TransitionManager.beginDelayedTransition(
                binding.transitionContainer,
                Slide(Gravity.TOP)
            )
            imageVisible = !imageVisible
            if (clickerFlag % 2 == 0) {
                binding.randomPlanet.setImageResource(planetsList.random())
            }
            clickerFlag++
            binding.randomPlanet.visibility = if (imageVisible) View.GONE else View.VISIBLE
        }
        binding.randomPlanet.setOnClickListener {
            isExpended = !isExpended
            val set = TransitionSet()
                .addTransition(ChangeBounds())
                .addTransition(ChangeImageTransform())

            TransitionManager.beginDelayedTransition(binding.transitionContainer, set)
            binding.randomPlanet.scaleType = if (isExpended) {
                ImageView.ScaleType.CENTER_CROP
            } else {
                ImageView.ScaleType.CENTER_INSIDE
            }
        }
    }
}