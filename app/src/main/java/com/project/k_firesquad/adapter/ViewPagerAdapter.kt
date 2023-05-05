package com.project.k_firesquad.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.project.k_firesquad.models.Buyer

class ViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){


    var onItemClick : ((Buyer) -> Unit)? = null
    private val mFrgmentList = ArrayList<Fragment>()
    private val mFrgmentTitleList = ArrayList<String>()


    override fun getCount() = mFrgmentList.size

    override fun getItem(position: Int) = mFrgmentList[position]

    override fun getPageTitle(position: Int) = mFrgmentTitleList[position]

    fun addFragment(fragment:Fragment,title:String){
        mFrgmentList.add(fragment)
        mFrgmentTitleList.add(title)
    }
}