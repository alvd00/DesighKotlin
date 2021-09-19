package com.example.desighkotlin.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desighkotlin.R

class PlanetFragment( private val code : Int) : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (code) {
            0 ->  inflater.inflate(R.layout.fragment_earth, container, false)
            1 ->  inflater.inflate(R.layout.fragment_mars, container, false)
            2 ->  inflater.inflate(R.layout.fragment_venus, container, false)
            else -> inflater.inflate(R.layout.fragment_earth, container, false)
        }
    }
}