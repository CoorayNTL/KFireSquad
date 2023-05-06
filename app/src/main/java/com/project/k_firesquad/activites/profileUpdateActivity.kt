package com.project.k_firesquad.activites

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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

        setTextValeus()

        submit.setOnClickListener {
            updateUserData()
        }


    }

    private fun setTextValeus() {
        companyName.setText(intent.getStringExtra("company_name"))
        email.setText(intent.getStringExtra("email"))
        mobile.setText(intent.getStringExtra("mobile"))
        address.setText(intent.getStringExtra("address"))
        password.setText(intent.getStringExtra("password"))
        username.setText(intent.getStringExtra("username"))

    }

    private fun updateUserData() {
        // Get the values from the text fields
        val cmpcompanyName = companyName.text.toString()
        val cmpemail = email.text.toString()
        val cmpmobile = mobile.text.toString()
        val cmpaddress = address.text.toString()
        val cmppassword = password.text.toString()
        val cmpusername = username.text.toString()


        val cmpData =
            CompanyData(cmpusername, cmppassword, cmpcompanyName, cmpemail, cmpmobile, cmpaddress)

        cmpusername?.let {
            FirebaseDatabase.getInstance().getReference("Users").child(it).setValue(cmpData)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Data Not Updated", Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }

}

