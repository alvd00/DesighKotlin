package com.example.desighkotlin.view.animation

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimationBinding
    private var imageVisible = true
    private var isExpended = false

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
            TransitionManager.beginDelayedTransition(
                binding.transitionContainer,
                Slide(Gravity.TOP)
            )
            imageVisible = !imageVisible
            if (clickerFlag % 2 == 0) {
                Toast.makeText(applicationContext, "${clickerFlag}" + "number", Toast.LENGTH_SHORT)
                Log.d("YYYY", "${clickerFlag}")
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