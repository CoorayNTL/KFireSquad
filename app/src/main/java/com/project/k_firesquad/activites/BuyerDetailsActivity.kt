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
            val textView_2 = findViewById<TextView>(R.id.textView10)
            val textView_3 = findViewById<TextView>(R.id.textView11)
            val textView_4 = findViewById<TextView>(R.id.textView12)
            val textView_5 = findViewById<TextView>(R.id.textView13)
            val textView_6 = findViewById<TextView>(R.id.buyer_Quantity)
            val textView_7 = findViewById<TextView>(R.id.description_header)
            val textView_8 = findViewById<TextView>(R.id.description_text)
            val textView_9 = findViewById<ImageView>(R.id.imageView4)
            val textView_10 = findViewById<ImageView>(R.id.imageView5)
            val textView_11 = findViewById<ImageView>(R.id.imageView3)
            val textView_12 = findViewById<ImageView>(R.id.imageView2)
            val textView_13 = findViewById<ImageView>(R.id.imageView)
            val textView_14 = findViewById<ImageView>(R.id.iv_buyer_image)

            textView_1.text = buyer.name

            textView_14.setImageResource(buyer.image)

        }

    }
}