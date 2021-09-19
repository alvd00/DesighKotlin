package com.example.desighkotlin.view.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.FragmentSettingsBinding
import com.example.desighkotlin.view.MainActivity
import com.google.android.material.chip.Chip
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import com.example.desighkotlin.view.ThemeOne
import com.example.desighkotlin.view.ThemeSecond


class SettingsFragment : Fragment(), View.OnClickListener {

    private lateinit var parentActivity: MainActivity // 1 способ получить родительскую активити
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = (context as MainActivity)
    }

    var _binding: FragmentSettingsBinding? = null
    val binding: FragmentSettingsBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      /*  binding.includeChips.chipGroup.setOnCheckedChangeListener { childGroup, position ->
            Toast.makeText(context, "Click $position", Toast.LENGTH_SHORT).show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.includeChips.chipGroup.forEachIndexed { index, view ->
                    (view as Chip).typeface = Typeface.DEFAULT
                }
            }
        }
        binding.includeChips.chipWithClose.setOnCloseIconClickListener {
            Toast.makeText(context, "Click on chipWithClose", Toast.LENGTH_SHORT).show()
        }
        binding.tabsSnake.getTabAt(0)?.apply {
            text = "Работает"
        }*/

        //  переключатель фрагмент
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_api_bottom_activity -> {
                    Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                }
                R.id.app_bar_settings -> {
                    Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }


        binding.btnThemeOne.setOnClickListener(this)
        binding.btnThemeSecond.setOnClickListener(this)
        when (parentActivity.getCurrentTheme()) {
            1 -> binding.radioGroup.check(R.id.btnThemeOne)
            2 -> binding.radioGroup.check(R.id.btnThemeSecond)
        }


        //depr
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_api_bottom_activity -> {
                    Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                    //Navigation().showFragment(FavoriteFragment.newInstance())
                }
                R.id.app_bar_settings -> {
                    Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                    // Navigation().showFragment(SettingsFragment.newInstance())
                }
            }
            true
        }
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnThemeOne -> {
                parentActivity.setCurrentTheme(ThemeOne)
                parentActivity.recreate()
            }
            R.id.btnThemeSecond -> {
                parentActivity.setCurrentTheme(ThemeSecond)
                parentActivity.recreate()
            }
        }

    }


    companion object {
        fun newInstance() = SettingsFragment()
    }


}
