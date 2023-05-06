package com.project.k_firesquad.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R

class FetchingActivity : AppCompatActivity() {

    private lateinit var empRecyclerView:RecyclerView
    private lateinit var tvLoadingData: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        empRecyclerView = findViewById(R.id.recycleView)

    }
}