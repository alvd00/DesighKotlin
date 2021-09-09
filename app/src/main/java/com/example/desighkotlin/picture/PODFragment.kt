package com.example.desighkotlin.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desighkotlin.databinding.FragmentMainBinding

class PODFragment : Fragment() {
    private var _binding : FragmentMainBinding?=null
    val binding:FragmentMainBinding
    get(){
        return _binding!!
    }

//    private val viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }
}