package com.project.k_firesquad.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.k_firesquad.R
import com.project.k_firesquad.adapter.DriverAdapter
import com.project.k_firesquad.databinding.DriverListActivityBinding
import com.project.k_firesquad.models.DataItem

class VehicleRecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: DriverListActivityBinding
    private lateinit var mList: ArrayList<DataItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DriverListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        mList = ArrayList()
        prepareData()


        val adapter = DriverAdapter(mList)
        binding.recycleView.adapter = adapter

    }

    private fun prepareData() {
        mList.add(
            DataItem(
                R.drawable.truck1,
                "TN 01 2345",
                "2-Axl-Lorry",
                "ABC Company",
                10000,
                "We provide professional moving services for residential and commercial customers",
                "9876543210",
                "Chennai"
            )
        )

        mList.add(
            DataItem(
                R.drawable.truck2,
                "TN 01 2345",
                "2-Axl-Lorry",
                "ABC Company",
                10000,
                "We provide professional moving services for residential and commercial customers",
                "9876543210",
                "Chennai"
            )
        )

        mList.add(
            DataItem(
                R.drawable.truck3,
                "TN 01 2345",
                "2-Axl-Lorry",
                "ABC Company",
                10000,
                "We provide professional moving services for residential and commercial customers",
                "9876543210",
                "Chennai"
            )
        )

        mList.add(
            DataItem(
                R.drawable.truck4,
                "TN 01 2345",
                "2-Axl-Lorry",
                "ABC Company",
                10000,
                "We provide professional moving services for residential and commercial customers",
                "9876543210",
                "Chennai"
            )
        )

        mList.add(
            DataItem(
                R.drawable.truck5,
                "TN 01 2345",
                "2-Axl-Lorry",
                "ABC Company",
                10000,
                "We provide professional moving services for residential and commercial customers",
                "9876543210",
                "Chennai"
            )
        )
    }
}