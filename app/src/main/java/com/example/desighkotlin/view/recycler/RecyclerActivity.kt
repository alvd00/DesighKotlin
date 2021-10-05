package com.example.desighkotlin.view.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.example.desighkotlin.databinding.ActivityRecyclerBinding
import com.google.android.material.snackbar.Snackbar

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data:MutableList<Pair<Data,Boolean>> = ArrayList()
        repeat(10) {
            if (it % 2 == 0) {
               // data.add(Data("Deal"))
            }
        }
        data.add(Pair(Data("Deal",""),false))

        data.add(0,Pair(Data("Header"),false))

        val adapter = RecyclerActivityAdapter(
            object : OnItemClickListener {
                override fun onItemClick(data: Data) {
                    Toast.makeText(this@RecyclerActivity, "THAT IS DATA"/*data.someText*/, Toast.LENGTH_SHORT).show()
                    Log.d("Test", "Pass")
                }
            }, data
        )
        binding.dealRecyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener { adapter.appendItem() }

    }
}
