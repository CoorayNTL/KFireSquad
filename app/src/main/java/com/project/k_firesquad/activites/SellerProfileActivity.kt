package com.project.k_firesquad.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.project.k_firesquad.R

class SellerProfileActivity : AppCompatActivity() {

    private lateinit var addProductBtn: ImageButton
    private lateinit var viewMyProducts: ImageButton
    private lateinit var marketPlace: ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_farmer_nav)

        addProductBtn = findViewById(R.id.addSellerProduct)
        viewMyProducts = findViewById(R.id.viewSellerProducts)
        marketPlace = findViewById(R.id.marketPlace)
        Log.d("hello", "intent.toString()")
        addProductBtn.setOnClickListener {
            val intent = Intent(this, SellerAddProductActivity::class.java)
            Log.d("hello", "intent.toString()")
            startActivity(intent)
        }
        viewMyProducts.setOnClickListener {
            val intent = Intent(this, SellerProductsListActivity::class.java)
            startActivity(intent)
        }
        marketPlace.setOnClickListener {
            val intent = Intent(this, SellerMarketPlaceActivity::class.java)
            startActivity(intent)
        }

    }
}