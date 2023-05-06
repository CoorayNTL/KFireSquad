package com.project.k_firesquad.activites

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.project.k_firesquad.R

class SellerProfileActivity : AppCompatActivity() {

    private lateinit var addProductBtn: ImageButton
    private lateinit var viewMyProducts: ImageButton
    private lateinit var marketPlace: ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_profile)

        addProductBtn = findViewById(R.id.addSellerProduct)
        viewMyProducts = findViewById(R.id.viewSellerProducts)
        marketPlace = findViewById(R.id.marketPlace)
        addProductBtn.setOnClickListener {
            val intent = Intent(this, SellerAddProductActivity::class.java)
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