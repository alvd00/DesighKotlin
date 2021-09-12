package com.example.desighkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import com.example.desighkotlin.R
import com.example.desighkotlin.view.picture.PODFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val changeTheme =findViewById<Button>(R.id.changeTheme)
        changeTheme.setOnClickListener {
            showPopup(it)



        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.main_container, PODFragment.newInstance()).commit()
        }

    }
    }
    private fun showPopup(view: View){ //создание меню
        var them = 1
        if (them==1){
            them=2
            getApplication().setTheme(R.style.Theme_DesighKotlin_myTheme)
        }
        else if(them==2){
            them = 1
            getApplication().setTheme(R.style.ThemeOverlay_AppCompat_DayNight)
        }
    }
}