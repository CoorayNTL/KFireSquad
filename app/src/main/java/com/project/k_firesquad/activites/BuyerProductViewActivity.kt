package com.project.k_firesquad.activites

import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import android.app.PendingIntent
import android.graphics.Color
import android.os.Build
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R

class BuyerProductViewActivity : AppCompatActivity() {

    //variables
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

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: NotificationCompat.Builder
    private val channelId = "my_channel_01"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_product_view)

        //call initView()
        initView()
        //call setValuesToViews()
        setValuesToViews()

        //set onclick listener to the update button
        btnUpdate.setOnClickListener {
            val currentIntent = intent
            val intent = Intent(this, BuyerUpdateProductActivity::class.java)
            intent.putExtra("buyerID", currentIntent.getStringExtra("buyerID"))
            intent.putExtra("buyerProductID", currentIntent.getStringExtra("buyerProductID"))
            intent.putExtra("sellerName", currentIntent.getStringExtra("sellerName"))
            intent.putExtra("sellerLocation", currentIntent.getStringExtra("sellerLocation"))
            intent.putExtra("productName", currentIntent.getStringExtra("productName"))
            intent.putExtra("productQty", currentIntent.getStringExtra("productQty"))
            intent.putExtra("productRate", currentIntent.getStringExtra("productRate"))
            intent.putExtra("description", currentIntent.getStringExtra("description"))
            intent.putExtra("offerEndDate", currentIntent.getStringExtra("offerEndDate"))
            intent.putExtra("offerStartDate", currentIntent.getStringExtra("offerStartDate"))
            startActivity(intent)
        }

        //set onclick listener to the delete button
        btnDelete.setOnClickListener {
            deleteRecord(intent.getStringExtra("buyerProductID").toString())
        }



        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,
                description,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }


    private fun showNotification() {
        val intent = Intent(this,BuyerProfileActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder=NotificationCompat.Builder(this,channelId)

                .setContentTitle("AGRO")
                .setContentText("Product Deleted Successfully")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }

        notificationManager.notify(1234, builder.build())
    }


    //initialize the view
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

    //set values to views
    private fun setValuesToViews() {

        sellerNameTextView.text = intent.getStringExtra("sellerName")
        sellerLocationTextView.text = intent.getStringExtra("sellerLocation")
        tvProductName.text = intent.getStringExtra("productName")
        tvQty.text = intent.getStringExtra("productQty")
        tvRate.text = "Rs.${intent.getStringExtra("productRate")}/1Kg"
        tvContact.text = "0887787678"
        tvDescription.text = intent.getStringExtra("description")

    }

    //delete the selected record
    private fun deleteRecord(productID: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("BuyerProducts").child(productID)
        val mTask = dbRef.removeValue()

        val builder= AlertDialog.Builder(this)
        builder.setTitle("Warning")
        builder.setMessage("Delete Product ?\n\nThis action cannot be undone")

        builder.setPositiveButton("OK"){dialog, which ->
            mTask.addOnSuccessListener {
                Toast.makeText(this, "Product Deleted Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, BuyerProfileActivity::class.java)
                finish()
                showNotification()
                startActivity(intent)
            }.addOnFailureListener { error ->
                Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
        val dialog=builder.create()
        dialog.show()


    }

}