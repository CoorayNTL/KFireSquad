package com.project.k_firesquad.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R
import com.project.k_firesquad.models.ExpertsPostModel

class MyPostsAdapter (private val myPostList : ArrayList<ExpertsPostModel>) : RecyclerView.Adapter<MyPostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.activity_my_post_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = myPostList[position]
        holder.tvPostTitle.text = currentPost.postTitle

        val currentMyPost = myPostList[position]
        holder.tvPostDesc.text = currentPost.postDesc
    }

    override fun getItemCount(): Int {
       return myPostList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvPostTitle : TextView = itemView.findViewById(R.id.tvPostTitle)
        val tvPostDesc : TextView = itemView.findViewById(R.id.tvPostDesc)
    }



}
