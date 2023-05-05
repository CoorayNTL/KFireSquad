package com.project.k_firesquad.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R
import com.project.k_firesquad.adapter.BuyerAdapter
import com.project.k_firesquad.models.Buyer

class BuyerActivity :AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var buyerArrayList: ArrayList<Buyer>
    private lateinit var buyerAdapter: BuyerAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_buyer)

        recyclerView = findViewById(R.id.rv_buyer)
        recyclerView.setHasFixedSize(true)
        buyerArrayList = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this)


        buyerArrayList.add(Buyer(R.drawable.cotton, "cotton"))
        buyerArrayList.add(Buyer(R.drawable.cucumber, "cucumber"))
        buyerArrayList.add(Buyer(R.drawable.marigold, "marigold"))
        buyerArrayList.add(Buyer(R.drawable.non_gmo_soya_bean, "soya_bean"))
        buyerArrayList.add(Buyer(R.drawable.orange, "orange"))
        buyerArrayList.add(Buyer(R.drawable.ginga, "ginga"))
        buyerArrayList.add(Buyer(R.drawable.marigold, "marigold"))
        buyerArrayList.add(Buyer(R.drawable.cucumber, "cucumber"))
        buyerArrayList.add(Buyer(R.drawable.potato, "potato"))

        buyerAdapter = BuyerAdapter(buyerArrayList)
        recyclerView.adapter = buyerAdapter

        buyerAdapter.onItemClick = {
            val intent = Intent(this, BuyerDetailsActivity::class.java)
            intent.putExtra("name", it.name)
            startActivity(intent)

        }

    }
}