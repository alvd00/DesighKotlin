package com.example.desighkotlin.view.picture

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.BottomNavigationLayoutBinding
import com.example.desighkotlin.utils.FAVORITE
import com.example.desighkotlin.utils.SETTINGS
import com.example.desighkotlin.view.animation.AnimationActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    val binding: BottomNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomNavigationLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.firstScreen ->{
                    activity?.let {
                        startActivity(Intent(it, AnimationActivity::class.java))
                    }
                    Toast.makeText(requireContext(), FAVORITE, Toast.LENGTH_SHORT).show()
                }
                R.id.settings_one ->{
                    Log.d("SETTT", "YEEEES")
                    Toast.makeText(context, SETTINGS, Toast.LENGTH_LONG).show()
                }
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {
        fun newInstance() = BottomNavigationDrawerFragment()
    }
}