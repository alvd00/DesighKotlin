package com.example.desighkotlin.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.FragmentMainBinding
import com.example.desighkotlin.viewmodel.PODData
import com.example.desighkotlin.viewmodel.PODViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PODFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var _binding : FragmentMainBinding?=null
    val binding:FragmentMainBinding
    get(){
        return _binding!!
    }

    private val viewModel : PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it)})
        viewModel.sendServerRequest()
        binding.inputLayout.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            }
            )

        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.includeLayout.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

    /*
        bottomSheetBehavior.addBottomSheetCallback(

            object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_DRAGGING -> TODO("not implemented")
                        BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                        BottomSheetBehavior.STATE_EXPANDED -> TODO("not implemented")
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                        BottomSheetBehavior.STATE_HIDDEN -> TODO("not implemented")
                        BottomSheetBehavior.STATE_SETTLING -> TODO("not implemented")
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    TODO("not implemented")
                }
            }

        )*/
    }

    private fun renderData(data:PODData){
        when (data){
            is PODData.Success -> {
                binding.customView.load(data.serverResponseData.url){
                    error(R.drawable.ic_load_error_vector)
                }}
            is PODData.Loading -> {
                Toast.makeText(context, "SOMETHING WRONG", LENGTH_LONG).show()
            }
            is PODData.Error -> {
                Toast.makeText(context, "ERROR", LENGTH_LONG).show()}

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object{
        fun newInstance()= PODFragment()}


}