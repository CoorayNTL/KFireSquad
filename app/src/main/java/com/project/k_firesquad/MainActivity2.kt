package com.project.k_firesquad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.activites.InsertionActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var regButton:Button
    private lateinit var loginButton:Button
    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        loginButton = findViewById(R.id.btnlogin)
        regButton = findViewById(R.id.regButton)

        loginButton.setOnClickListener {
            loginChecker()
        }

        regButton.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)

            startActivity(intent)
        }

    }

    private fun loginChecker( ) {
        val usernameInput = email.text.toString().trim()
        val passwordInput = password.text.toString().trim()

        if (usernameInput.isEmpty()) {
            email.error = "email is required"
            email.requestFocus()
            return
        }

        if (passwordInput.isEmpty()) {
            password.error = "Password is required"
            password.requestFocus()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameInput, passwordInput)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login successful. Start the MainActivityProfile activity
                    val intent = Intent(this@MainActivity2, MainActivityProfile::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Login failed. Show an error message
                    Toast.makeText(this@MainActivity2, "Login failed. Please check your credentials and try again.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}