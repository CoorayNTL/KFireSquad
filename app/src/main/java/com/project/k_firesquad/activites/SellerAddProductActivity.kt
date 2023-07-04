package com.project.k_firesquad.activites

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.project.k_firesquad.R
import com.project.k_firesquad.models.SellerProduct

class SellerAddProductActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etProductQty: EditText
    private lateinit var etProductRate: EditText
    private lateinit var etDescription: EditText
    private lateinit var etSellerName: EditText
    private lateinit var etSellerLocation: EditText
    private lateinit var btnUploadImage: FloatingActionButton
    private lateinit var submitButton: Button


    private lateinit var dbRef: DatabaseReference
    private lateinit var storageRef: StorageReference

    private var uri: Uri? = null

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: NotificationCompat.Builder
    private val channelId = "my_channel_01"
    private val description = "Test notification"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_add_products)
        initializeFields()




        //Firebase Realtime Database
        dbRef = FirebaseDatabase.getInstance().getReference("SellerProducts")

        //Firebase Storage
        storageRef = FirebaseStorage.getInstance().getReference("Images")


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


        submitButton.setOnClickListener {
            saveSellerProductData()
            showNotification()
            //Log.d("Values", values)

        }




        //Pick Image from Gallery
//        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
//            btnUploadImage.setImageURI(it)
//            if (it != null) {
//                uri = it
//            }
//        }
//
//        //store image in storage
//        btnUploadImage.setOnClickListener {
//            pickImage.launch("image/*")
//        }


    }

    private fun showNotification() {
        val intent = Intent(this, SellerProfileActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("AGRO")
            .setContentText("Farmer Data add Successful")
            .setSmallIcon(R.drawable.notification_icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        notificationManager.notify(1234, builder.build())
    }


    private fun initializeFields() {
        etProductName = findViewById(R.id.etProductName)
        etProductQty = findViewById(R.id.etProductQty)
        etProductRate = findViewById(R.id.etProductRate)
        etDescription = findViewById(R.id.etDescription)
        etSellerName = findViewById(R.id.etSellerName)
        etSellerLocation = findViewById(R.id.etSellerLocation)

        submitButton = findViewById(R.id.submitButton)
    }

    private fun saveSellerProductData() {
        val productName = etProductName.text.toString()
        val productQty = etProductQty.text.toString()
        val productRate = etProductRate.text.toString()
        val description = etDescription.text.toString()
        val sellerName = etSellerName.text.toString()
        val sellerLocation = etSellerLocation.text.toString()

        val sellerProductID = dbRef.push().key!!
        val sellerID = dbRef.push().key!!

        //validation
        if (productName.isEmpty()) {
            etProductName.error = "Please Enter the Product Name"
            return
        }

        if (productQty.isEmpty()) {
            etProductQty.error = "Please Enter the Quantity"
            return
        }

        if (productRate.isEmpty()) {
            etProductRate.error = "Please Enter the Product Rate"
            return
        }

        if (description.isEmpty()) {
            etDescription.error = "Please Enter the Product Description"
            return
        }

        if (sellerName.isEmpty()) {
            etSellerName.error = "Please Enter the Seller Name"
            return
        }

        if (sellerLocation.isEmpty()) {
            etSellerLocation.error = "Please Enter the Seller Location"
            return
        }



        val sellerProduct = SellerProduct(
                        sellerProductID,
                        productName,
                        productQty,
                        productRate,
                        description,
                        sellerName,
                        sellerLocation,

                        sellerID,

                    )
//        uri.let {
//
//            storageRef.child(sellerProductID).putFile(it!!).addOnSuccessListener { task ->
//
//                task.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
//
//                    Toast.makeText(this, "Image Uploaded", Toast.LENGTH_SHORT).show()
//                    val ImageUrlsellerProduct = uri.toString()
//
//
//
//
//                }
//            }
//
//
//        }
        dbRef.child(sellerProductID).setValue(sellerProduct).addOnCompleteListener {
            Toast.makeText(this, "Added Product", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SellerProfileActivity::class.java)
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
        }


//        return "$productName $productQty $productRate $description $sellerName $sellerLocation $offerStartDate $offerEndDate"
    }

}