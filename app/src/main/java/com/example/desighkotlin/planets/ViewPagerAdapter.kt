package com.example.desighkotlin.planets

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val EARTH = 0
private const val MARS = 1
private const val VENUS = 2

class ViewPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), VenusFragment())
    override fun getItemCount(): Int {
        return fragments.size
    }


    override fun createFragment(position: Int): Fragment {
        //return PlanetFragment(position)

        return when (position) {
            0 -> fragments[EARTH]
            1 -> fragments[MARS]
            2 -> fragments[VENUS]
            else -> fragments[EARTH]
        }
    }

}