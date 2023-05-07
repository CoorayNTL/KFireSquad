package com.project.k_firesquad.activites

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R
import com.project.k_firesquad.models.VehicleData
import androidx.appcompat.app.AlertDialog
import com.project.k_firesquad.MainActivity
import com.project.k_firesquad.MainActivityProfile
import com.project.k_firesquad.MainActivityVehcileProfile

class addVehicleActivity: AppCompatActivity() {
    private lateinit var vehicleNo:EditText
    private lateinit var vehicleType:EditText
    private lateinit var owner:EditText
    private lateinit var contact:EditText
    private lateinit var price:EditText
    private lateinit var about:EditText
    private lateinit var location:EditText
    private lateinit var submit: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_vehicle)

        vehicleNo = findViewById(R.id.vehicleNo)
        vehicleType = findViewById(R.id.vehicleType)
        owner = findViewById(R.id.companyName)
        contact = findViewById(R.id.contactNo)
        price = findViewById(R.id.rentCost)
        about = findViewById(R.id.aboutVehicle)
        location = findViewById(R.id.location)
        submit= findViewById(R.id.submitButton)


        dbRef = FirebaseDatabase.getInstance().getReference("Vehicles")

        submit.setOnClickListener {
            saveVehicleData()
        }


    }

    private fun saveVehicleData() {

        // Get the values from the text fields
        val v_vehicleNo = vehicleNo.text.toString()
        val v_vehicleType = vehicleType.text.toString()
        val v_owner = owner.text.toString()
        val v_contact = contact.text.toString()
        val v_price = price.text.toString()
        val v_about = about.text.toString()
        val v_location = location.text.toString()

        // Check if the values are empty
        if (v_vehicleNo.isEmpty()) {
            vehicleNo.error = "Please enter a vehicle number"
            return
        }
        if (v_vehicleType.isEmpty()) {
            vehicleType.error = "Please enter a vehicle type"
            return
        }
        if (v_owner.isEmpty()) {
            owner.error = "Please enter the owner's name"
            return
        }
        if (v_contact.isEmpty()) {
            contact.error = "Please enter a contact number"
            return
        }
        if (v_price.isEmpty()) {
            price.error = "Please enter a price"
            return
        }
        if (v_about.isEmpty()) {
            about.error = "Please enter a description"
            return
        }
        if (v_location.isEmpty()) {
            location.error = "Please enter a location"
            return
        }


        val vehicleId = dbRef.push().key!!

        val vehicle = VehicleData(vehicleId, v_vehicleNo, v_vehicleType, v_owner, v_contact, v_price, v_about, v_location)

        dbRef.child(vehicleId).setValue(vehicle).addOnCompleteListener {task->
            if(task.isSuccessful){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Success")
                builder.setMessage("Vehicle added successfully")

                builder.setPositiveButton("OK"){dialog, which ->
                    val intent=Intent(this, MainActivityVehcileProfile::class.java)

                    // Pass the values to the next activity
                    intent.putExtra("vehicleNo", v_vehicleNo)
                    intent.putExtra("vehicleType", v_vehicleType)
                    intent.putExtra("owner", v_owner)
                    intent.putExtra("contact", v_contact)
                    intent.putExtra("price", v_price)
                    intent.putExtra("about", v_about)
                    intent.putExtra("location", v_location)
                    intent.putExtra("vehicleId", vehicleId)

                    startActivity(intent)
                }

                val dialog=builder.create()
                dialog.show()

            }
        }.addOnFailureListener(){error->
//            Toast.makeText(this, "Failed to add user", Toast.LENGTH_SHORT).show()
            val builder=AlertDialog.Builder(this)
            builder.setTitle("Failed")
            builder.setMessage("Failed to add the Vehicle")
            builder.setPositiveButton("OK"){
                    dialog,which->
                dialog.dismiss()
            }
        }


    }
}