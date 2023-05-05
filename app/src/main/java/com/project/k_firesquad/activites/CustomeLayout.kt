package com.project.k_firesquad.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.project.k_firesquad.R
import com.project.k_firesquad.fragments.FirstFragment
import com.project.k_firesquad.fragments.SecondFragment
import com.project.k_firesquad.fragments.ThirdFragment
import com.project.k_firesquad.adapter.ViewPagerAdapter
import com.project.k_firesquad.databinding.ActivityMainBinding

class CustomeLayout :AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custome_layout)

        val view = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tbLayout)

        val adapter=ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(FirstFragment(),"First")
        adapter.addFragment(SecondFragment(),"Second")
        adapter.addFragment(ThirdFragment(),"Third")

        view.adapter=adapter
        tabLayout.setupWithViewPager(view)

//        binding= ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val adapter= ViewPagerAdapter(supportFragmentManager)
//
//        adapter.addFragment(FirstFragment(),"First")
//        adapter.addFragment(SecondFragment(),"Second")
//        adapter.addFragment(ThirdFragment(),"Third")
//
//        binding.viewPager.adapter=adapter
//        binding.tbLayout.setupWithViewPager(binding.viewPager)

    }


}