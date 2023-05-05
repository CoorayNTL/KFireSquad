package com.project.k_firesquad.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.project.k_firesquad.R
import com.project.k_firesquad.models.Buyer

@Suppress("DEPRECATION")
class BuyerDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_details)

        val buyer = intent.getParcelableExtra<Buyer>("name")
        if (buyer != null) {

            val textView_1 = findViewById<TextView>(R.id.tv_buyer_name)

            val textView_14 = findViewById<ImageView>(R.id.iv_buyer_image)

            textView_1.text = buyer.name

            textView_14.setImageResource(buyer.image)

        }

    }
}