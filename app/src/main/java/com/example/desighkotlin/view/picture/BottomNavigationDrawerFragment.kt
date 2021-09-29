package com.example.desighkotlin.view.picture

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.BottomNavigationLayoutBinding
import com.example.desighkotlin.utils.FAVORITE
import com.example.desighkotlin.utils.SETTINGS
import com.example.desighkotlin.view.animation.FirstAnimationActivity
import com.example.desighkotlin.view.animation.SecondAnimationActivity
import com.example.desighkotlin.view.recycler.RecyclerActivity
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
                R.id.firstScreenAnimation ->{
                    activity?.let {
                        startActivity(Intent(it, FirstAnimationActivity::class.java))
                    }
                    Toast.makeText(requireContext(), FAVORITE, Toast.LENGTH_SHORT).show()
                }
                R.id.secondScreenAnimation ->{
                    activity?.let {
                        startActivity(Intent(it, SecondAnimationActivity::class.java))
                    }
                    Toast.makeText(context, SETTINGS, Toast.LENGTH_LONG).show()
                }
                R.id.recycle ->{
                    activity?.let {
                        startActivity(Intent(it, RecyclerActivity::class.java))
                    }
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