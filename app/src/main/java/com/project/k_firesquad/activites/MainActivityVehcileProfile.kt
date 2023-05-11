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

class MainActivityVehcileProfile : AppCompatActivity() {
    
    private lateinit var vehicleNo:TextView
    private lateinit var vehicleType:TextView
    private lateinit var owner:TextView
    private lateinit var contact:TextView
    private lateinit var price:TextView
    private lateinit var about:TextView
    private lateinit var location:TextView
    private lateinit var id:TextView
    private lateinit var btnBacktoprofile:Button

    
    private lateinit var btnUpdateVehicle: Button
    private lateinit var btnDeleteVehicle: Button
    
    private lateinit var databaseReference: DatabaseReference
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_vehcile_profile)
        
        
        btnUpdateVehicle = findViewById(R.id.btnUpdateProfile)
        btnDeleteVehicle = findViewById(R.id.btnDeleteAccount)
        btnBacktoprofile = findViewById(R.id.btnBacktoprofile)
        
        
        btnUpdateVehicle.setOnClickListener {
            //val intent = Intent(this,profileUpdateActivity::class.java)
            //startActivity(intent)
        }
        
        btnDeleteVehicle.setOnClickListener {
            deleteRecord(intent.getStringExtra("vehicleId").toString())
        }

        btnBacktoprofile.setOnClickListener {
            val intent = Intent(this, DriverActivityProfile::class.java)
            startActivity(intent)
        }


        //initialize the Text Views
        vehicleNo = findViewById(R.id.vehicleNo)
        vehicleType = findViewById(R.id.vehicle_type)
        owner = findViewById(R.id.item_company)
        contact = findViewById(R.id.item_contact)
        price = findViewById(R.id.item_price)
        about=findViewById(R.id.item_description)
        location=findViewById(R.id.item_location)
        id=findViewById(R.id.vehicleid)
        
        //get the data from the intent
        intent.extras?.let {
            vehicleNo.text = it.getString("vehicleNo")
            vehicleType.text = it.getString("vehicleType")
            owner.text = it.getString("owner")
            contact.text = it.getString("contact")
            price.text = it.getString("price")
            about.text = it.getString("about")
            location.text = it.getString("location")
            id.text = it.getString("vehicleId")
            
            
        }
    }

    private fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Vehicles")
        val query: Query = dbRef.orderByChild("vehicleId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val userKey = userSnapshot.key
                        if (userKey != null) {
                            dbRef.child(userKey).removeValue().addOnSuccessListener {
                                Toast.makeText(this@MainActivityVehcileProfile, "Account Deleted Successfully", Toast.LENGTH_SHORT).show()
//                                FirebaseAuth.getInstance().signOut()
//                                val intent = Intent(this@MainActivityVehcileProfile,DriverActivityProfile::class.java)
//                                startActivity(intent)
//                                finish()
                            }.addOnFailureListener {
                                Toast.makeText(this@MainActivityVehcileProfile, "Error Occurred", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivityVehcileProfile, "User not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MainActivityVehcileProfile, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
        })
    }
}