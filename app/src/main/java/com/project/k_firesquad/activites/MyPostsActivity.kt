package com.project.k_firesquad.activites

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.project.k_firesquad.R
import com.project.k_firesquad.adapter.MyPostsAdapter
import com.project.k_firesquad.models.ExpertsPostModel

class MyPostsActivity : AppCompatActivity() {

    private lateinit var myPostsRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var myPostList :  ArrayList<ExpertsPostModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_posts)

        myPostsRecyclerView = findViewById(R.id.rvMyPosts)
        myPostsRecyclerView.layoutManager = LinearLayoutManager(this)
        myPostsRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        myPostList = arrayListOf<ExpertsPostModel>()

        getMyPostsData()

    }

    private fun getMyPostsData(){
        myPostsRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Posts")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                myPostList.clear()
                if (snapshot.exists()){
                    for (myPostsSnap in snapshot.children){
                        val myPostData = myPostsSnap.getValue(ExpertsPostModel::class.java)
                        myPostList.add(myPostData!!)
                    }
                    val mAdapter = MyPostsAdapter(myPostList)
                    myPostsRecyclerView.adapter = mAdapter


                    mAdapter.setOnItemClickListener(object : MyPostsAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent =  Intent(this@MyPostsActivity, MyPostDetailsActivity::class.java)

                            //put extra
                            intent.putExtra("postID",myPostList[position].postID)
                            intent.putExtra("postTitle",myPostList[position].postTitle)
                            intent.putExtra("postDesc",myPostList[position].postDesc)
                            startActivity(intent)
                        }

                    })

                    myPostsRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}