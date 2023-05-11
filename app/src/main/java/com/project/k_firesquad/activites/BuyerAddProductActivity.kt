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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R
import com.project.k_firesquad.models.BuyerProduct
import java.text.SimpleDateFormat
import java.util.*

class BuyerAddProductActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etProductQty: EditText
    private lateinit var etProductRate: EditText
    private lateinit var etDescription: EditText
    private lateinit var etSellerName: EditText
    private lateinit var etSellerLocation: EditText
    private lateinit var etOfferStartDate: EditText
    private lateinit var etOfferEndDate: EditText
    //    private lateinit var btnUploadImage: Button
    private lateinit var submitButton: Button
    private lateinit var calendar: Calendar

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: NotificationCompat.Builder
    private val channelId = "my_channel_01"
    private val description = "Test notification"

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_add_products)
        initializeFields()

        dbRef = FirebaseDatabase.getInstance().getReference("BuyerProducts")

        //submit button
        submitButton.setOnClickListener {
            saveBuyerProductData()
        }
        calendar = Calendar.getInstance()

        //date pickers
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

    //date listeners
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
                .setContentText("Product Added Successfully")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }

        notificationManager.notify(1234, builder.build())
    }

    //initialize input fields
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
        submitButton = findViewById(R.id.submitButton)
    }

    //Save Products function
    private fun saveBuyerProductData()  {
        val productName =  etProductName.text.toString()
        val productQty = etProductQty.text.toString()
        val productRate = etProductRate.text.toString()
        val description = etDescription.text.toString()
        val sellerName = etSellerName.text.toString()
        val sellerLocation = etSellerLocation.text.toString()
        val offerStartDate = etOfferStartDate.text.toString()
        val offerEndDate = etOfferEndDate.text.toString()
        val buyerProductID = dbRef.push().key!!
        val buyerID = dbRef.push().key!!

        //validation
        if(productName.isEmpty()){
            etProductName.error = "Please Enter the Product Name"
            return
        }

        if(productQty.isEmpty()){
            etProductQty.error = "Please Enter the Quantity"
            return
        }

        if(productRate.isEmpty()){
            etProductRate.error = "Please Enter the Product Rate"
            return
        }

        if(description.isEmpty()){
            etDescription.error = "Please Enter the Product Description"
            return
        }

        if(sellerName.isEmpty()){
            etSellerName.error = "Please Enter the Seller Name"
            return
        }

        if(sellerLocation.isEmpty()){
            etSellerLocation.error = "Please Enter the Seller Location"
            return
        }

        if(offerStartDate.isEmpty()){
            etOfferStartDate.error = "Please Enter the Offer Start Date"
            return
        }

        if(offerEndDate.isEmpty()){
            etOfferEndDate.error = "Please Enter the Offer End Date"
            return
        }

        val buyerProduct = BuyerProduct(buyerProductID, productName, productQty, productRate, description, sellerName, sellerLocation, offerStartDate, offerEndDate, buyerID)

        dbRef.child(buyerProductID).setValue(buyerProduct).addOnCompleteListener {
            Toast.makeText(this, "Added Product", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, BuyerProfileActivity::class.java)
            showNotification()
            startActivity(intent)
        }.addOnFailureListener {error ->
            Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
        }



    }

}