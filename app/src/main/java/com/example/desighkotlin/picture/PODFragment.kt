package com.example.desighkotlin.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.FragmentMainBinding
import com.example.desighkotlin.viewmodel.PODData
import com.example.desighkotlin.viewmodel.PODViewModel

class PODFragment : Fragment() {
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
                Toast.makeText(context, "ERRRRROR", LENGTH_LONG).show()}

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object{
        fun newInstance()= PODFragment()}


}