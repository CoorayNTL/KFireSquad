package com.project.k_firesquad.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.project.k_firesquad.R
import com.project.k_firesquad.utlies.ProductQuantityUpdateListener
import com.project.k_firesquad.utlies.ProductQuantityUpdater

class BuyerProductRequestActivity : AppCompatActivity(), ProductQuantityUpdateListener {
    private lateinit var sellerNameTextView: TextView
    private lateinit var sellerLocationTextView: TextView
    private lateinit var tvProductName: TextView
    private lateinit var tvQty: TextView
    private lateinit var tvRate: TextView
    private lateinit var tvContact: TextView
    private lateinit var tvDescription: TextView
    private lateinit var etQty: TextView
    private lateinit var btnRequest: Button

    override fun onQuantityUpdated(updatedQty: Int) {
        // update the UI with the updated quantity
        tvQty.text = updatedQty.toString()
    }

    override fun onInvalidQuantity() {
        // show an error message to the user
        Toast.makeText(this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_product_request)

        initView()
        setValuesToViews()

        val updater = ProductQuantityUpdater(this)
        btnRequest.setOnClickListener {
            val productQty = tvQty.text.toString().toInt()
            val insertedQty = etQty.text.toString().toInt()
            updater.updateQuantity(productQty, insertedQty)
        }

    }

    private fun initView() {
        sellerNameTextView = findViewById(R.id.sellerNameTextView)
        sellerLocationTextView = findViewById(R.id.sellerLocationTextView)
        tvProductName = findViewById(R.id.tvProductName)
        tvQty = findViewById(R.id.tvQty)
        tvRate = findViewById(R.id.tvRate)
        tvContact = findViewById(R.id.tvContact)
        tvDescription = findViewById(R.id.tvDescription)
        etQty = findViewById(R.id.etQty)
        btnRequest = findViewById(R.id.btnRequest)
    }


    private fun setValuesToViews() {

        sellerNameTextView.text = intent.getStringExtra("sellerName")
        sellerLocationTextView.text = intent.getStringExtra("sellerLocation")
        tvProductName.text = intent.getStringExtra("productName")
        tvQty.text = intent.getStringExtra("productQty")
        tvRate.text = "Rs.${intent.getStringExtra("productRate")}/1Kg"
        tvContact.text = "0887787678"
        tvDescription.text = intent.getStringExtra("description")

    }

     fun updateQuantity(productQty: Int, insertedQty: Int ): Int{
        var updatedQty = 0
        if(insertedQty >= productQty ){
            Toast.makeText(this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show()
        }else{
            updatedQty =   productQty - insertedQty
            tvQty.text = updatedQty.toString()
            Log.d("updated", updatedQty.toString())


        }

        return updatedQty
    }

}