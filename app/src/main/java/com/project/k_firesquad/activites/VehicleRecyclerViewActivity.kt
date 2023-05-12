package com.project.k_firesquad.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.project.k_firesquad.R
import com.project.k_firesquad.adapter.DriverAdapter
import com.project.k_firesquad.models.VehicleData

class VehicleRecyclerViewActivity : AppCompatActivity() {

    private lateinit var empRecyclerView:RecyclerView

    private lateinit var empList:ArrayList<VehicleData>
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        empRecyclerView = findViewById(R.id.recycleView)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)

        empList = arrayListOf<VehicleData>()

        getEmployeeData()





    }

    private fun getEmployeeData() {

        empRecyclerView.visibility= View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Vehicles")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if(snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val emp = empSnap.getValue(VehicleData::class.java)
                        empList.add(emp!!)
                    }

                    val mAdapter = DriverAdapter(empList)
                    empRecyclerView.adapter = mAdapter
                    empRecyclerView.visibility= View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO()
            }

        })
}
}