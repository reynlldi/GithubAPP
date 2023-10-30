package com.example.github.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.github.ui.DetailUserActivity
import com.example.github.ui.FollowersFragment
import com.example.github.ui.FollowingFragment

class SectionsPagerAdapter(activity: DetailUserActivity, data: Bundle) : FragmentStateAdapter(activity) {

    private var fragmentBundle : Bundle

    init {
        fragmentBundle = data
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

}