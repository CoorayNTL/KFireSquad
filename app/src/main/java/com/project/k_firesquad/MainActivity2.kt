package com.project.k_firesquad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.project.k_firesquad.activites.InsertionActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var regButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



//        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()


        regButton = findViewById(R.id.regButton)

        regButton.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }


    }
}