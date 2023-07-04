package com.project.k_firesquad.activites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.project.k_firesquad.R
import com.project.k_firesquad.models.CompanyData

class profileUpdateActivity: AppCompatActivity() {

    private lateinit var mobile: EditText
    private lateinit var email: EditText
    private lateinit var address: EditText
    private lateinit var submit: Button
    private lateinit var password: EditText
    private lateinit var username: EditText
    private lateinit var companyName: EditText
    private lateinit var database: FirebaseDatabase
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_owner_account)

        companyName = findViewById(R.id.company_name)
        email = findViewById(R.id.email)
        mobile = findViewById(R.id.mobile)
        address = findViewById(R.id.text_address)
        password = findViewById(R.id.password)
        username = findViewById(R.id.username)
        submit = findViewById(R.id.btnUpdateProfile)
        database = FirebaseDatabase.getInstance()
        btnBack = findViewById(R.id.btnBack)

        setTextValues()

        submit.setOnClickListener {
            updateUserData()
        }

        btnBack.setOnClickListener {
            intent = Intent(this,DriverActivityProfile::class.java)
            finish()
        }
    }

    private fun setTextValues() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let { user ->
            val userId = user.uid
            val userRef = database.getReference("Users").child(userId)
            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val usernameValue = dataSnapshot.child("username").value.toString()
                        val emailValue = dataSnapshot.child("email").value.toString()
                        val mobileValue = dataSnapshot.child("contact").value.toString()
                        val addressValue = dataSnapshot.child("address").value.toString()
                        val companyNameValue = dataSnapshot.child("company_name").value.toString()

                        //set the data to the text views
                        username.setText(usernameValue)
                        email.setText(emailValue)
                        mobile.setText(mobileValue)
                        address.setText(addressValue)
                        companyName.setText(companyNameValue)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "Error reading user data", databaseError.toException())
                }
            })
        } ?: run {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUserData() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let { user ->
            val userId = user.uid
            val userRef = database.getReference("Users").child(userId)
            val updateMap = mutableMapOf<String, Any>()

            if (!companyName.text.isNullOrEmpty()) {
                updateMap["company_name"] = companyName.text.toString()
            }
            if (!email.text.isNullOrEmpty()) {
                user.updateEmail(email.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            updateMap["email"] = email.text.toString()
                            userRef.updateChildren(updateMap)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { exception ->
                                    Log.e(TAG, "Error updating user data", exception)
                                    Toast.makeText(this, "Data Not Updated", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(this, "Failed to update email", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                userRef.updateChildren(updateMap)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { exception ->
                        Log.e(TAG, "Error updating user data", exception)
                        Toast.makeText(this, "Data Not Updated", Toast.LENGTH_SHORT).show()
                    }
            }
            if (!mobile.text.isNullOrEmpty()) {
                updateMap["contact"] = mobile.text.toString()
            }
            if (!address.text.isNullOrEmpty()) {
                updateMap["address"] = address.text.toString()
            }
            if (!password.text.isNullOrEmpty()) {
                user.updatePassword(password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        } ?: run {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "profileUpdateActivity"
    }

}

