package com.project.k_firesquad.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.project.k_firesquad.R

class MainActivity : AppCompatActivity() {

//    private lateinit var btnInsertData: Button
//    private lateinit var btnFetchData: Button
    private lateinit var btnExpertsActions: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        btnInsertData = findViewById(R.id.btnInsertData)
//        btnFetchData = findViewById(R.id.btnFetchData)
        btnExpertsActions = findViewById(R.id.btnExpertsActions)

        btnExpertsActions.setOnClickListener{
            val intent = Intent(this, ExpertActionsActivity::class.java)
            startActivity(intent)
        }

//        btnInsertData.setOnClickListener {
//            val intent = Intent(this, AddPostActivity::class.java)
//            startActivity(intent)
//        }
//
//        btnFetchData.setOnClickListener {
//            val intent = Intent(this, MyPostsActivity::class.java)
//            startActivity(intent)
//        }


    }
}

