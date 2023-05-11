package com.project.k_firesquad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.multiplerecyclerviewmultiviewtype.DataItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.k_firesquad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mList: ArrayList<DataItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)

//        val appBarConfiguration  = AppBarConfiguration(setOf(R.id.farmer_nav,R.id.seller_nav,R.id.agro_nav,R.id.driver_nav))
//        setupActionBarWithNavController(navController,appBarConfiguration)

        buttonNavigationView.setupWithNavController(navController)
//
//        binding.mainRecyclerView.setHasFixedSize(true)
//        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
//
//        mList = ArrayList()
//        prepareData()
//
//        val adapter = MainAdapter(mList)
//        binding.mainRecyclerView.adapter = adapter
//    }

//    private fun prepareData() {
//
//        // best seller
//        val bestSellerList = ArrayList<RecyclerItem>()
//        bestSellerList.add(RecyclerItem(R.drawable.group71 , "Up to 20% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.group74, "Up to 10% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.group74, "Up to 40% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.group71, "Up to 20% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.group71 , "Up to 20% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.group74, "Up to 10% off"))
//
//        //product
//        val productList = ArrayList<RecyclerItem>()
//        productList.add(RecyclerItem(R.drawable.istockphoto_1007817460_612x6121, "Up to 25% off"))
//        productList.add(RecyclerItem(R.drawable.labour_shortage_in_indian_agriculture, "Up to 30% off"))
//        productList.add(RecyclerItem(R.drawable.labour_shortage_in_indian_agriculture, "Up to 35% off"))
//        productList.add(RecyclerItem(R.drawable.istockphoto_1007817460_612x6121, "Up to 25% off"))
//        productList.add(RecyclerItem(R.drawable.istockphoto_1007817460_612x6121, "Up to 30% off"))
//        productList.add(RecyclerItem(R.drawable.labour_shortage_in_indian_agriculture, "Up to 35% off"))
//
//        mList.add(DataItem(DataItemType.BEST_SELLER, bestSellerList))
//        mList.add(DataItem(DataItemType.BANNER, Banner(R.drawable.sellerbanner)))
//        mList.add(DataItem(DataItemType.PRODUCT, productList))
//        mList.add(DataItem(DataItemType.BANNER, Banner(R.drawable.farmerbanner1)))
//        mList.add(DataItem(DataItemType.BEST_SELLER, bestSellerList.asReversed()))
//        mList.add(DataItem(DataItemType.BANNER, Banner(R.drawable.sellerbanner)))
   }
}