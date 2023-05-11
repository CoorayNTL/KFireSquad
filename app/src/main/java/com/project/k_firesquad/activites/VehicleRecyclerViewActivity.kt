package com.project.k_firesquad.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.project.k_firesquad.adapter.DriverAdapter
import com.project.k_firesquad.databinding.ActivityMainBinding
import com.project.k_firesquad.models.VehicleData

class VehicleRecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DriverAdapter
    private lateinit var vehicleDataList: ArrayList<VehicleData>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        vehicleDataList = ArrayList()
        adapter = DriverAdapter(vehicleDataList)
        binding.recycleView.adapter = adapter

        database = FirebaseDatabase.getInstance().reference.child("vehicles")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                vehicleDataList.clear()
                for (vehicleSnapshot in snapshot.children) {
                    val vehicleData = vehicleSnapshot.getValue(VehicleData::class.java)
                    vehicleData?.let {
                        vehicleDataList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}