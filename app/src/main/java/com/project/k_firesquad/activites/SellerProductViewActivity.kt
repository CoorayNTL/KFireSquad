package com.project.k_firesquad.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R

class SellerProductViewActivity : AppCompatActivity() {

    private lateinit var sellerNameTextView: TextView
    private lateinit var sellerLocationTextView: TextView
    private lateinit var tvProductName: TextView
    private lateinit var tvQty: TextView
    private lateinit var tvRate: TextView
    private lateinit var tvContact: TextView
    private lateinit var tvDescription: TextView
    private lateinit var etQty: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_product_view)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            val currentIntent = intent
            val intent = Intent(this, SellerUpdateProductActivity::class.java)
            intent.putExtra("sellerID", currentIntent.getStringExtra("sellerID"))
            intent.putExtra("sellerProductID", currentIntent.getStringExtra("sellerProductID"))
            intent.putExtra("sellerName", currentIntent.getStringExtra("sellerName"))
            intent.putExtra("sellerLocation", currentIntent.getStringExtra("sellerLocation"))
            intent.putExtra("productName", currentIntent.getStringExtra("productName"))
            intent.putExtra("productQty", currentIntent.getStringExtra("productQty"))
            intent.putExtra("productRate", currentIntent.getStringExtra("productRate"))
            intent.putExtra("description", currentIntent.getStringExtra("description"))

            startActivity(intent)
        }


        btnDelete.setOnClickListener {
            deleteRecord(intent.getStringExtra("sellerProductID").toString())
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
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
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

    private fun deleteRecord(productID: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("SellerProducts").child(productID)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Product  Deleted", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SellerProfileActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
        }
    }

}