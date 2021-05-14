package org.revunit.coolapkkt.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.revunit.coolapkkt.ui.fragment.CoolPicImageFragment

class CoolPicInfoViewPagerAdapter(
    images: ArrayList<String>,
    fm: FragmentManager
) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments: ArrayList<Fragment> = ArrayList(images.map { CoolPicImageFragment(it) })
    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]
}