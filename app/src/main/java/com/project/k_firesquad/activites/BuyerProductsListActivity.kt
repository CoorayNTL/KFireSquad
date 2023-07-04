package com.project.k_firesquad.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.project.k_firesquad.R
import com.project.k_firesquad.adapter.BuyerProductListAdapter
import com.project.k_firesquad.models.BuyerProduct
import java.util.*
import kotlin.collections.ArrayList

class BuyerProductsListActivity : AppCompatActivity() {

    //variables
    private lateinit var buyerProductsRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private var buyerProductsList= ArrayList<BuyerProduct>()
    private lateinit var dbRef: DatabaseReference
    private lateinit var searchView: SearchView
    private lateinit var mAdapter: BuyerProductListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_products_list)


        //initialize variables
        buyerProductsRecyclerView = findViewById(R.id.rvEmp)
        searchView = findViewById(R.id.searchView)
        mAdapter = BuyerProductListAdapter(buyerProductsList)
        buyerProductsRecyclerView.layoutManager = LinearLayoutManager(this)
        buyerProductsRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)


        buyerProductsList = arrayListOf<BuyerProduct>()

        //call the getBuyerProductsData()
        getBuyerProductsData()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }

    private fun filterList(query: String?){
        if(query != null){
            val filteredList = ArrayList<BuyerProduct>()
            for(i in buyerProductsList){
                if (i.productName?.lowercase(Locale.ROOT)?.contains(query) == true){
                    filteredList.add(i)
                }
            }

            if(filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }
            else{
                mAdapter.setFilteredList(filteredList)
            }
        }
    }


    //get product details of the buyer
    private fun getBuyerProductsData() {

        buyerProductsRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        //initialize firebase connection
        dbRef = FirebaseDatabase.getInstance().getReference("BuyerProducts")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                buyerProductsList.clear()
                if (snapshot.exists()){
                    for (buyerProductSnap in snapshot.children){
                        val buyerProduct = buyerProductSnap.getValue(BuyerProduct::class.java)
                        buyerProductsList.add(buyerProduct!!)
                    }

                    mAdapter = BuyerProductListAdapter(buyerProductsList)
                    buyerProductsRecyclerView.adapter = mAdapter
                    mAdapter.setOnItemClickListener(object : BuyerProductListAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@BuyerProductsListActivity, BuyerProductViewActivity::class.java)

                            //put extras
                            intent.putExtra("buyerID", buyerProductsList[position].buyerID)
                            intent.putExtra("buyerProductID", buyerProductsList[position].buyerProductID)
                            intent.putExtra("sellerName", buyerProductsList[position].sellerName)
                            intent.putExtra("sellerLocation", buyerProductsList[position].sellerLocation)
                            intent.putExtra("productName", buyerProductsList[position].productName)
                            intent.putExtra("productQty", buyerProductsList[position].productQty)
                            intent.putExtra("productRate", buyerProductsList[position].productRate)
                            intent.putExtra("description", buyerProductsList[position].description)
                            intent.putExtra("offerEndDate", buyerProductsList[position].offerEndDate)
                            intent.putExtra("offerStartDate", buyerProductsList[position].offerStartDate)
                            startActivity(intent)
                        }

                    })

                    buyerProductsRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
