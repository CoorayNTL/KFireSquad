package com.project.k_firesquad.activites

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R

class FetchingActivity : AppCompatActivity() {

    private lateinit var empRecyclerView:RecyclerView
    private lateinit var tvLoadingData: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_list_activity)

        empRecyclerView = findViewById(R.id.recycleView)

    }
}