package com.lusi.movieapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lusi.movieapp.fragment.NowPlayingFragment
import com.lusi.movieapp.fragment.PopularFragment
import com.lusi.movieapp.fragment.TopRatedFragment
import com.lusi.movieapp.fragment.UpcomingFragment

class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    val fragments: ArrayList<Fragment> = arrayListOf(
        PopularFragment(),
        NowPlayingFragment(),
        TopRatedFragment(),
        UpcomingFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}