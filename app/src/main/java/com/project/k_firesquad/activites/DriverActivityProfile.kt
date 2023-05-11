package com.project.k_firesquad.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.project.k_firesquad.R
import com.project.k_firesquad.utlies.LoginPageActivity

class DriverActivityProfile : AppCompatActivity() {

    private lateinit var btnAddVehicle: Button
    private lateinit var btnUpdateProfile: Button
    private lateinit var btnDeleteAccount: Button
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var mobileTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var companyNameTextView: TextView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var btnLogout: Button
    private lateinit var btnViewVehicles: Button




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profile)

        btnAddVehicle = findViewById(R.id.btnAddVehicle)
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile)
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount)
        btnLogout = findViewById(R.id.btnLogout)
        btnViewVehicles = findViewById(R.id.btnViewVehicles)

        btnAddVehicle.setOnClickListener {
            val intent = Intent(this, addVehicleActivity::class.java)
            startActivity(intent)

        }

        btnViewVehicles.setOnClickListener {
            val intent = Intent(this, VehicleRecyclerViewActivity::class.java)
            startActivity(intent)
        }

        btnUpdateProfile.setOnClickListener {
            val intent = Intent(this, profileUpdateActivity::class.java)
            startActivity(intent)
        }

        btnDeleteAccount.setOnClickListener {
            deleteRecord()
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@DriverActivityProfile, LoginPageActivity::class.java)
            startActivity(intent)
            finish()
        }


        //initialize the Text Views
        usernameTextView = findViewById(R.id.usernameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        mobileTextView = findViewById(R.id.mobileTextView)
        addressTextView = findViewById(R.id.addressTextView)
        companyNameTextView = findViewById(R.id.companyNameTextView)

//        //get the data from the intent
//        intent.extras?.let {
//
//            val username = it.getString("username")
//            val email = it.getString("email")
//            val mobile = it.getString("contact")
//            val address = it.getString("address")
//            val companyName = it.getString("company_name")
//
//
//            //set the data to the text views
//            usernameTextView.text = username
//            emailTextView.text = email
//            mobileTextView.text = mobile
//            addressTextView.text = address
//            companyNameTextView.text = companyName
//        }

        // Get a reference to the Firebase Realtime Database
        val database = FirebaseDatabase.getInstance()

        // Get a reference to the current user's node in the database
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val userRef = database.getReference("Users").child(userId)

        // Add a ValueEventListener to the user node reference
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val username = dataSnapshot.child("username").value.toString()
                    val email = dataSnapshot.child("email").value.toString()
                    val mobile = dataSnapshot.child("contact").value.toString()
                    val address = dataSnapshot.child("address").value.toString()
                    val companyName = dataSnapshot.child("company_name").value.toString()

                    // Set the data to the text views
                    usernameTextView.text = username
                    emailTextView.text = email
                    mobileTextView.text = mobile
                    addressTextView.text = address
                    companyNameTextView.text = companyName
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error

            }
        })



    }

    private fun deleteRecord() {
        val dbRef = FirebaseDatabase.getInstance().getReference("Users")
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        dbRef.child(userId).removeValue().addOnSuccessListener {
            Toast.makeText(this@DriverActivityProfile, "Account Deleted Successfully", Toast.LENGTH_SHORT).show()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@DriverActivityProfile, LoginPageActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@DriverActivityProfile, "Error Occurred", Toast.LENGTH_SHORT).show()
        }
    }

}
