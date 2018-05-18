package com.winqoo.wikia.ui.onboarding.slider

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
internal class SlideAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return SlideFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 3
    }

}
