package com.project.k_firesquad.activites

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R
import com.project.k_firesquad.models.SellerProduct
import java.text.SimpleDateFormat
import java.util.*

class SellerUpdateProductActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etProductQty: EditText
    private lateinit var etProductRate: EditText
    private lateinit var etDescription: EditText
    private lateinit var etSellerName: EditText
    private lateinit var etSellerLocation: EditText
    private lateinit var btnUploadImage: FloatingActionButton
    private lateinit var sellerProductsRecyclerView: RecyclerView
    private lateinit var updateButton: Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_update_product)
        initializeFields()
        setTextValues()



        updateButton.setOnClickListener {
            updateEmpData()
            sellerProductsRecyclerView.adapter?.notifyDataSetChanged()

        }



    }




    private fun initializeFields()  {
        etProductName = findViewById(R.id.etProductName)
        etProductQty = findViewById(R.id.etProductQty)
        etProductRate = findViewById(R.id.etProductRate)
        etDescription = findViewById(R.id.etDescription)
        etSellerName = findViewById(R.id.etSellerName)
        etSellerLocation = findViewById(R.id.etSellerLocation)

        btnUploadImage = findViewById(R.id.btnUploadImage)
        updateButton = findViewById(R.id.updateProductButton)
    }

    private fun setTextValues(){
        etProductName.setText(intent.getStringExtra("productName"))
        etProductQty.setText(intent.getStringExtra("productQty"))
        etProductRate.setText(intent.getStringExtra("productRate"))
        etDescription.setText(intent.getStringExtra("productName"))
        etSellerName.setText(intent.getStringExtra("sellerName"))
        etSellerLocation.setText(intent.getStringExtra("sellerLocation"))

    }

    private fun updateEmpData() {
        val productName = etProductName.text.toString()
        val productQty = etProductQty.text.toString()
        val productRate = etProductRate.text.toString()
        val description = etDescription.text.toString()
        val sellerName = etSellerName.text.toString()
        val sellerLocation = etSellerLocation.text.toString()

        val sellerProductID = intent.getStringExtra("sellerProductID")
        val sellerID = intent.getStringExtra("sellerID")

        val empInfo = SellerProduct(
            sellerProductID,
            productName,
            productQty,
            productRate,
            description,
            sellerName,
            sellerLocation,

            sellerID
        )
        sellerProductID?.let {
            FirebaseDatabase.getInstance().getReference("SellerProducts").child(
                it
            )
        }?.setValue(empInfo)
        Toast.makeText(applicationContext, "Product Data Updated", Toast.LENGTH_SHORT).show()
    }
}