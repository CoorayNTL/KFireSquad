package com.project.k_firesquad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.project.k_firesquad.activites.addVehicleActivity
import com.project.k_firesquad.activites.profileUpdateActivity

class MainActivityProfile : AppCompatActivity() {

    private lateinit var btnAddVehicle: Button
    private lateinit var btnUpdateProfile: Button
    private lateinit var btnDeleteAccount: Button
    private lateinit var usernameTextView:TextView
    private lateinit var emailTextView:TextView
    private lateinit var mobileTextView:TextView
    private lateinit var addressTextView:TextView
    private lateinit var companyNameTextView: TextView
    private lateinit var databaseReference: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profile)

        btnAddVehicle = findViewById(R.id.btnAddVehicle)
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile)
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount)

        btnAddVehicle.setOnClickListener {
            val intent = Intent(this,addVehicleActivity::class.java)
            startActivity(intent)

        }

        btnUpdateProfile.setOnClickListener {
            val intent = Intent(this,profileUpdateActivity::class.java)
            startActivity(intent)
        }

        btnDeleteAccount.setOnClickListener {
            deleteRecord(intent.getStringExtra("username").toString())
        }

        //initialize the Text Views
        usernameTextView=findViewById(R.id.usernameTextView)
        emailTextView=findViewById(R.id.emailTextView)
        mobileTextView=findViewById(R.id.mobileTextView)
        addressTextView=findViewById(R.id.addressTextView)
        companyNameTextView=findViewById(R.id.companyNameTextView)

        //get the data from the intent
        intent.extras?.let {
            val username = it.getString("username")
            val email = it.getString("email")
            val mobile = it.getString("contact")
            val address = it.getString("address")
            val companyName = it.getString("company_name")


            //set the data to the text views
            usernameTextView.text = username
            emailTextView.text = email
            mobileTextView.text = mobile
            addressTextView.text = address
            companyNameTextView.text = companyName
        }



    }

    private fun deleteRecord(username: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Users")
        val query: Query = dbRef.orderByChild("username").equalTo(username)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val userKey = userSnapshot.key
                        if (userKey != null) {
                            dbRef.child(userKey).removeValue().addOnSuccessListener {
                                Toast.makeText(this@MainActivityProfile, "Account Deleted Successfully", Toast.LENGTH_SHORT).show()
                                FirebaseAuth.getInstance().signOut()
                                val intent = Intent(this@MainActivityProfile, MainActivity2::class.java)
                                startActivity(intent)
                                finish()
                            }.addOnFailureListener {
                                Toast.makeText(this@MainActivityProfile, "Error Occurred", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivityProfile, "User not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MainActivityProfile, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
        })
    }


}