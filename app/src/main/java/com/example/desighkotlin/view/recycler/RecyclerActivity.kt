package com.example.desighkotlin.view.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding : ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data:MutableList<Data> = ArrayList()
        repeat(10){
            if(it%2==0){
                data.add(Data("Deal"))
            }
        }
        data.add(0,Data("Header"))

        binding.dealRecyclerView.adapter = RecyclerActivityAdapter(
            object : OnItemListClickListener {
                override fun onItemClick(data: Data) {
                    Toast.makeText(this@RecyclerActivity,data.someText,Toast.LENGTH_SHORT).show()
                }
            }, data)
    }
}