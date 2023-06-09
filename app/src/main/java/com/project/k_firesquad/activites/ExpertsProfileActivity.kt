package com.project.k_firesquad.activites

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.project.k_firesquad.R

class ExpertsProfileActivity : AppCompatActivity() {

    private lateinit var btnExpertsActions: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experts_profile)

        btnExpertsActions = findViewById(R.id.btnExpertsActions)

        btnExpertsActions.setOnClickListener{
            val intent = Intent(this, ExpertActionsActivity::class.java)
            startActivity(intent)
        }
    }
}