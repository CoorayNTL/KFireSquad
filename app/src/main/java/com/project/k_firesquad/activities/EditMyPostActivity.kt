package com.project.k_firesquad.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R

class EditMyPostActivity : AppCompatActivity() {

    private lateinit var tvPostTitle: TextView
    private lateinit var tvPostDesc: TextView
    private lateinit var btnEditPost:Button
    private lateinit var btnDeletePost:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_post_items)

//        btnEditPost.setOnClickListener{
//            openUpdateDialog(
//                intent.getStringArrayExtra("postID").toString()
//
//            )
//        }

        btnDeletePost.setOnClickListener{
            deleteRecord(
                intent.getStringArrayExtra("postID").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Posts").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Post deleted successfully",Toast.LENGTH_LONG).show()
            val intent = Intent(this, MyPostsActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this,"Post deleting error ${error.message}",Toast.LENGTH_LONG).show()
        }
    }
}