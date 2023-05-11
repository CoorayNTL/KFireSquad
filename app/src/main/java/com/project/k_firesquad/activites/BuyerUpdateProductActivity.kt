package com.project.k_firesquad.activites

import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import android.app.PendingIntent
import android.graphics.Color
import android.os.Build
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R
import com.project.k_firesquad.models.BuyerProduct
import java.text.SimpleDateFormat
import java.util.*

class BuyerUpdateProductActivity : AppCompatActivity() {

    //variables
    private lateinit var etProductName: EditText
    private lateinit var etProductQty: EditText
    private lateinit var etProductRate: EditText
    private lateinit var etDescription: EditText
    private lateinit var etSellerName: EditText
    private lateinit var etSellerLocation: EditText
    private lateinit var etOfferStartDate: EditText
    private lateinit var etOfferEndDate: EditText
    //    private lateinit var btnUploadImage: Button
    private lateinit var buyerProductsRecyclerView: RecyclerView
    private lateinit var updateButton: Button
    private lateinit var calendar: Calendar

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: NotificationCompat.Builder
    private val channelId = "my_channel_01"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_update_product)
        initializeFields()
        setTextValues()

        calendar = Calendar.getInstance()

        //date select handler
        etOfferStartDate.setOnClickListener {

            DatePickerDialog(this, startDateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        etOfferEndDate.setOnClickListener {
            DatePickerDialog(
                this, endDateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        //set onclick listener to update button
        updateButton.setOnClickListener {
            updateEmpData()

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

    //date selectors
    private val startDateSetListener =
        DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())

            etOfferStartDate.setText(sdf.format(calendar.time))



        }

    private val endDateSetListener =
        DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())

            etOfferEndDate.setText(sdf.format(calendar.time))



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
                .setContentText("Product Updated Successfully")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }

        notificationManager.notify(1234, builder.build())
    }



    //initializing fields
    private fun initializeFields()  {
        etProductName = findViewById(R.id.etProductName)
        etProductQty = findViewById(R.id.etProductQty)
        etProductRate = findViewById(R.id.etProductRate)
        etDescription = findViewById(R.id.etDescription)
        etSellerName = findViewById(R.id.etSellerName)
        etSellerLocation = findViewById(R.id.etSellerLocation)
        etOfferStartDate = findViewById(R.id.etOfferStartDate)
        etOfferEndDate = findViewById(R.id.etOfferEndDate)
//        btnUploadImage = findViewById(R.id.btnUploadImage)
        updateButton = findViewById(R.id.updateProductButton)
    }

    //set previous values to the text values
    private fun setTextValues(){
        etProductName.setText(intent.getStringExtra("productName"))
        etProductQty.setText(intent.getStringExtra("productQty"))
        etProductRate.setText(intent.getStringExtra("productRate"))
        etDescription.setText(intent.getStringExtra("productName"))
        etSellerName.setText(intent.getStringExtra("sellerName"))
        etSellerLocation.setText(intent.getStringExtra("sellerLocation"))
        etOfferStartDate.setText(intent.getStringExtra("offerEndDate"))
        etOfferEndDate.setText(intent.getStringExtra("offerStartDate"))
    }

    //update the data
    private fun updateEmpData() {
        val productName = etProductName.text.toString()
        val productQty = etProductQty.text.toString()
        val productRate = etProductRate.text.toString()
        val description = etDescription.text.toString()
        val sellerName = etSellerName.text.toString()
        val sellerLocation = etSellerLocation.text.toString()
        val offerStartDate = etOfferStartDate.text.toString()
        val offerEndDate = etOfferEndDate.text.toString()
        val buyerProductID = intent.getStringExtra("buyerProductID")
        val buyerID = intent.getStringExtra("buyerID")

        val empInfo = BuyerProduct(
            buyerProductID,
            productName,
            productQty,
            productRate,
            description,
            sellerName,
            sellerLocation,
            offerStartDate,
            offerEndDate,
            buyerID
        )
        buyerProductID?.let {
            FirebaseDatabase.getInstance().getReference("BuyerProducts").child(
                it
            )
        }?.setValue(empInfo)
        Toast.makeText(applicationContext, "Product Data Updated", Toast.LENGTH_SHORT).show()
        showNotification()
    }
}