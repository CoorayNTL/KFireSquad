package com.project.k_firesquad.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.project.k_firesquad.R

class BuyerProfileActivity : AppCompatActivity() {

    private lateinit var addProductBtn: ImageButton
    private lateinit var viewMyProducts: ImageButton
    private lateinit var marketPlace: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_profile)

        addProductBtn = findViewById(R.id.addBuyerProduct)
        viewMyProducts = findViewById(R.id.viewBuyerProducts)
        marketPlace = findViewById(R.id.marketPlace)
        addProductBtn.setOnClickListener {
            val intent = Intent(this, BuyerAddProductActivity::class.java)
            startActivity(intent)
        }
        viewMyProducts.setOnClickListener {
            val intent = Intent(this, BuyerProductsListActivity::class.java)
            startActivity(intent)
        }
        marketPlace.setOnClickListener {
            val intent = Intent(this, BuyerMarketPlaceActivity::class.java)
            startActivity(intent)
        }

    }
}