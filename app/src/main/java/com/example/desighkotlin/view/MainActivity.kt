package com.example.desighkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desighkotlin.R
import com.example.desighkotlin.view.picture.PODFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.main_container, PODFragment.newInstance()).commit()
        }
    }
}