package com.project.k_firesquad.activites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.MainActivity2
import com.project.k_firesquad.MainActivityProfile
import com.project.k_firesquad.R
import com.project.k_firesquad.models.CompanyData

class InsertionActivity: AppCompatActivity() {

    private lateinit var name:EditText
    private lateinit var mobile:EditText
    private lateinit var email:EditText
    private lateinit var address:EditText
    private lateinit var submit:Button
    private lateinit var password:EditText
    private lateinit var username:EditText

    private lateinit var dbRef: DatabaseReference


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
        if(cmpemail.isEmpty()){
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



        val userId = dbRef.push().key!!

        val user = CompanyData(userId,cmpusername,cmppassword ,cmpname, cmpemail, cmpmobile, cmpaddress)

        dbRef.child(userId).setValue(user).addOnCompleteListener {task->
//            Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()

            if(task.isSuccessful){
                val builder=AlertDialog.Builder(this)
                builder.setTitle("Success")
                builder.setMessage("User added successfully")

                builder.setPositiveButton("OK"){dialog, which ->
                    val intent= Intent(this,MainActivityProfile::class.java)

                    intent.putExtra("company_name", cmpname)
                    intent.putExtra("email", cmpemail)
                    intent.putExtra("contact", cmpmobile)
                    intent.putExtra("address", cmpaddress)
                    intent.putExtra("username", cmpusername)

                    startActivity(intent)
                }
                val dialog=builder.create()
                dialog.show()
            }

//            val intent = Intent(this,MainActivity2::class.java)
//            startActivity(intent)
        }.addOnFailureListener(){error->
//            Toast.makeText(this, "Failed to add user", Toast.LENGTH_SHORT).show()
            val builder=AlertDialog.Builder(this)
            builder.setTitle("Failed")
            builder.setMessage("Failed to add user")
            builder.setPositiveButton("OK"){
                dialog,which->
                dialog.dismiss()
            }
            }
        }
//
//        if (userId != null) {
//            dbRef.child(userId).setValue(user).addOnCompleteListener {
//                Toast.makeText(applicationContext, "User added successfully", Toast.LENGTH_SHORT).show()
//            }
//        }





}
