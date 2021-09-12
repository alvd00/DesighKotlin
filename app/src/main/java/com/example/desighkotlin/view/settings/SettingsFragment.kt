package com.example.desighkotlin.view.settings

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
import com.google.android.material.chip.Chip

class SettingsFragment : Fragment() {
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

        binding.includeChips.chipGroup.setOnCheckedChangeListener { childGroup, position ->
            Toast.makeText(context, "Click $position", Toast.LENGTH_SHORT).show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.includeChips.chipGroup.forEachIndexed { index, view ->
                    (view as Chip).typeface = Typeface.DEFAULT
                }
                //(childGroup.getChildAt(position-1) as Chip).typeface = resources.getFont(R.font.a)
            }
        }
        binding.includeChips.chipWithClose.setOnCloseIconClickListener {
            Toast.makeText(context, "Click on chipWithClose", Toast.LENGTH_SHORT).show()
        }
        binding.tabsSnake.getTabAt(0)?.apply {
            text = "Работает"
        }

        // Акцентируем внимание на лучшем на сегодняшний момент переключателе фрагмент
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.app_bar_fav -> {
                    Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                    //  Navigation().showFragment(FavoriteFragment.newInstance())
                }
                R.id.app_bar_settings -> {
                    Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                    // Navigation().showFragment(SettingsFragment.newInstance())
                }
            }
            true
        }

        //depr
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.app_bar_fav -> {
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

    companion object {
        fun newInstance() = SettingsFragment()
    }

}