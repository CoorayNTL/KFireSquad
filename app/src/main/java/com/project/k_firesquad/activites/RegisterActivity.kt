package com.project.k_firesquad.activites

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R
import com.project.k_firesquad.models.CompanyData
import com.project.k_firesquad.utlies.LoginPageActivity

class RegisterActivity: AppCompatActivity() {

    private lateinit var name:EditText
    private lateinit var mobile:EditText
    private lateinit var email:EditText
    private lateinit var address:EditText
    private lateinit var submit:Button
    private lateinit var password:EditText
    private lateinit var username:EditText

    private lateinit var dbRef: DatabaseReference

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId="my_channel_01"
    private val description="AGRO Notification"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        name = findViewById(R.id.name) //company name
        email= findViewById(R.id.email) //company email
        mobile = findViewById(R.id.mobile) //company mobile
        address = findViewById(R.id.text_address) //company address
        submit= findViewById(R.id.submitButton) //submit button
        password = findViewById(R.id.password) //company password
        username = findViewById(R.id.username) //company username

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


        dbRef = FirebaseDatabase.getInstance().getReference("Users")


        submit.setOnClickListener {
            saveUserData()
        }
    }

    private fun saveUserData(){
        // Get the values from the text fields
        val cmpname = name.text.toString()
        val cmpemail = email.text.toString()
        val cmpmobile = mobile.text.toString()
        val cmpaddress = address.text.toString()
        val cmppassword = password.text.toString()
        val cmpusername = username.text.toString()

        // Validation
        if(cmpname.isEmpty()){
            name.error = "Please enter your name"
            return
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(cmpemail).matches()){
            email.error = "Please enter your email"
            return
        }
        if(cmpmobile.isEmpty()){
            mobile.error = "Please enter your mobile"
            return
        }
        if(cmpaddress.isEmpty()){
            address.error = "Please enter your address"
            return
        }
        if(cmppassword.isEmpty()){
            password.error = "Please enter your password"
            return
        }
        if(cmpusername.isEmpty()){
            username.error = "Please enter your username"
            return
        }

        // Create a user account in Firebase Authentication
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(cmpemail, cmppassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = FirebaseAuth.getInstance().currentUser!!.uid
                    val user = CompanyData(userId, cmpusername, cmppassword, cmpname, cmpemail, cmpmobile, cmpaddress)

                    // Save the user's data to the Firebase Realtime Database
                    FirebaseDatabase.getInstance().getReference("Users").child(userId).setValue(user)
                        .addOnCompleteListener {
                            val builder=AlertDialog.Builder(this)
                            builder.setTitle("Success")
                            builder.setMessage("User added successfully")

                            builder.setPositiveButton("OK"){dialog, which ->
                                val intent= Intent(this, LoginPageActivity::class.java)

                                //passing data to next activity
                                intent.putExtra("company_name", cmpname)
                                intent.putExtra("email", cmpemail)
                                intent.putExtra("contact", cmpmobile)
                                intent.putExtra("address", cmpaddress)
                                intent.putExtra("username", cmpusername)

                                showNotification()
                                startActivity(intent)
                            }
                            val dialog=builder.create()
                            dialog.show()
                        }
                        .addOnFailureListener { error ->
                            val builder=AlertDialog.Builder(this)
                            builder.setTitle("Failed")
                            builder.setMessage("Failed to add user")
                            builder.setPositiveButton("OK"){
                                    dialog,which->
                                dialog.dismiss()
                            }

                            val dialog=builder.create()
                            dialog.show()
                        }
                } else {
                    Toast.makeText(this, "Failed to create user account", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun showNotification() {
        val intent = Intent(this,LoginPageActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder=Notification.Builder(this,channelId)
                .setContentTitle("AGRO")
                .setContentText("User added successfully")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }

        notificationManager.notify(1234, builder.build())
    }
//
//        if (userId != null) {
//            dbRef.child(userId).setValue(user).addOnCompleteListener {
//                Toast.makeText(applicationContext, "User added successfully", Toast.LENGTH_SHORT).show()
//            }
//        }





}
