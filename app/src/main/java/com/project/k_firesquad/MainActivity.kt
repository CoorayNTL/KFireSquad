package com.project.k_firesquad

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.project.k_firesquad.activites.CustomeLayout
import com.project.k_firesquad.adapter.ViewPagerAdapter
import com.project.k_firesquad.databinding.ActivityMainBinding
import com.project.k_firesquad.fragments.FirstFragment
import com.project.k_firesquad.fragments.SecondFragment
import com.project.k_firesquad.fragments.ThirdFragment

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter=ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(FirstFragment(),"First")
        adapter.addFragment(SecondFragment(),"Second")
        adapter.addFragment(ThirdFragment(),"Third")

        binding.viewPager.adapter=adapter
        binding.tbLayout.setupWithViewPager(binding.viewPager)

    }


//    private lateinit var recyclerView: RecyclerView
//    private lateinit var buyerArrayList: ArrayList<Buyer>
//    private lateinit var buyerAdapter: BuyerAdapter
//    private lateinit var viewPager: ViewPager
//    private lateinit var tbLayout: TabLayout
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        recyclerView = findViewById(R.id.rv_buyer)
//        recyclerView.setHasFixedSize(true)
//        buyerArrayList = ArrayList()
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//
//        buyerArrayList.add(Buyer(R.drawable.cotton,"cotton"))
//        buyerArrayList.add(Buyer(R.drawable.cucumber,"cucumber"))
//        buyerArrayList.add(Buyer(R.drawable.marigold,"marigold"))
//        buyerArrayList.add(Buyer(R.drawable.non_gmo_soya_bean,"soya_bean"))
//        buyerArrayList.add(Buyer(R.drawable.orange,"orange"))
//        buyerArrayList.add(Buyer(R.drawable.ginga,"ginga"))
//        buyerArrayList.add(Buyer(R.drawable.marigold,"marigold"))
//        buyerArrayList.add(Buyer(R.drawable.cucumber,"cucumber"))
//        buyerArrayList.add(Buyer(R.drawable.potato,"potato"))
//
//        buyerAdapter = BuyerAdapter(buyerArrayList)
//        recyclerView.adapter = buyerAdapter
//
//        buyerAdapter.onItemClick = {
//            val intent = Intent(this, BuyerDetailsActivity::class.java)
//            intent.putExtra("name", it.name)
//            startActivity(intent)
//
//        }
//
//
//        viewPager = findViewById(R.id.viewPager)
//        tbLayout = findViewById(R.id.tbLayout)
//
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(FirstFragment(), "First")
//        adapter.addFragment(SecondFragment(), "Second")
//        adapter.addFragment(ThirdFragment(), "Third")
//        viewPager.adapter = adapter
//
//        tbLayout.setupWithViewPager(viewPager)
//
//        adapter.onItemClick = {
//            val intent = Intent(this, CustomeLayout::class.java)
//            intent.putExtra("name", it)
//            startActivity(intent)
//        }




}