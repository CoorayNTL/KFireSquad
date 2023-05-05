package com.project.k_firesquad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.project.k_firesquad.activites.profileUpdateActivity

class MainActivityProfile : AppCompatActivity() {

    private lateinit var btnAddVehicle: Button
    private lateinit var btnUpdateProfile: Button
    private lateinit var btnDeleteAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profile)

        btnAddVehicle = findViewById(R.id.btnAddVehicle)
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile)
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount)

        btnAddVehicle.setOnClickListener {
//            val intent = Intent(this, AddVehicleActivity::class.java)
//            startActivity(intent)
        }

        btnUpdateProfile.setOnClickListener {
            val intent = Intent(this,profileUpdateActivity::class.java)
            startActivity(intent)
        }

        btnDeleteAccount.setOnClickListener {
//            val intent = Intent(this, DeleteAccountActivity::class.java)
//            startActivity(intent)
        }

    }
}