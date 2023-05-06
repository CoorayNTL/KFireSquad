package com.project.k_firesquad.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import com.project.k_firesquad.R
import com.project.k_firesquad.adapter.SellerMarketPlaceAdapter
import com.project.k_firesquad.models.SellerProduct

class SellerMarketPlaceActivity : AppCompatActivity() {

    private lateinit var sellerProductsRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var sellerProductsList: ArrayList<SellerProduct>
    private lateinit var dbRef: DatabaseReference
  //  private lateinit var storageRef: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_market_place)



        sellerProductsRecyclerView = findViewById(R.id.rvEmp)
        sellerProductsRecyclerView.layoutManager = LinearLayoutManager(this)
        sellerProductsRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        sellerProductsList = arrayListOf<SellerProduct>()

        getSellerProductsData()

    }


    private fun getSellerProductsData() {

        sellerProductsRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("SellerProducts")
      ///  storageRef =
           // com.google.firebase.storage.FirebaseStorage.getInstance().getReference("Images")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                sellerProductsList.clear()
                if (snapshot.exists()) {
                    for (sellerProductSnap in snapshot.children) {
                        val sellerProduct = sellerProductSnap.getValue(SellerProduct::class.java)
                        sellerProductsList.add(sellerProduct!!)
                    }
                    val mAdapter = SellerMarketPlaceAdapter(sellerProductsList)
                    sellerProductsRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object :
                        SellerMarketPlaceAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(
                                this@SellerMarketPlaceActivity,
                                SellerProductRequestActivity::class.java
                            )

//                            //put extras
                            intent.putExtra("sellerID", sellerProductsList[position].sellerID)
                            intent.putExtra(
                                "sellerProductID",
                                sellerProductsList[position].sellerProductID
                            )
                            intent.putExtra("sellerName", sellerProductsList[position].sellerName)
                            intent.putExtra(
                                "sellerLocation",
                                sellerProductsList[position].sellerLocation
                            )
                            intent.putExtra("productName", sellerProductsList[position].productName)
                            intent.putExtra("productQty", sellerProductsList[position].productQty)
                            intent.putExtra("productRate", sellerProductsList[position].productRate)
                            intent.putExtra("description", sellerProductsList[position].description)

                            startActivity(intent)
                        }

                    })

                    sellerProductsRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

//        storageRef.child("Images").child("SellerProducts")
//            .child("sellerProductID").downloadUrl.addOnSuccessListener {uri ->
//
//            // Got the download URL for 'users/me/profile.png'
//                val url = uri.toString()
//
//            }.addOnFailureListener {
//                // Handle any errors
//
//            }
    }
}

