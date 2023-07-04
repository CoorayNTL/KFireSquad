package com.project.k_firesquad.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R
import com.project.k_firesquad.models.ExpertsPostModel

class AddPostActivity : AppCompatActivity() {

    private lateinit var postTitle: EditText
    private lateinit var postDesc: EditText
    private lateinit var btnAddPost: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post_form)

        postTitle = findViewById(R.id.enterPostTitle)
        postDesc = findViewById(R.id.enterPostDes)
        btnAddPost = findViewById(R.id.btnAddPost)

        dbRef = FirebaseDatabase.getInstance().getReference("Posts")

        btnAddPost.setOnClickListener(){
            savePostsData()
        }
    }

    private fun savePostsData(){
        //getting values
        val tvPostTitle = postTitle.text.toString()
        val tvPostDesc = postDesc.text.toString()

//     if (postTitle.isEmpty()){
//          postTitle.error = "Please enter post title"
//     }

        val postID = dbRef.push().key!!

        val posts = ExpertsPostModel(postID,tvPostTitle,tvPostDesc)

        dbRef.child(postID).setValue(posts)
            .addOnCompleteListener() {
                Toast.makeText(this,"Data Added Successfully", Toast.LENGTH_LONG).show()

              postTitle.text.clear()
              postDesc.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
