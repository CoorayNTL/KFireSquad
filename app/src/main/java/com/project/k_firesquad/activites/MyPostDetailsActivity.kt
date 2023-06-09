package com.project.k_firesquad.activites

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R
import com.project.k_firesquad.models.ExpertsPostModel

class MyPostDetailsActivity : AppCompatActivity() {

    private lateinit var tvPostId: TextView
    private lateinit var tvPostTitle: TextView
    private lateinit var tvPostDesc: TextView
    private lateinit var btnEditPost: Button
    private lateinit var btnDeletePost: Button
    private lateinit var like: CheckBox
    private lateinit var textView :TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_post_details)
        //setContentView(R.layout.activity_update_post_details)

        initView()
        setValuesToViews()

        btnEditPost.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("postId").toString() ,
                intent.getStringExtra("postTitle").toString()
            )
        }

        btnDeletePost.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("postID").toString()
            )
        }

        like = findViewById(R.id.like)
        like.setOnCheckedChangeListener{ checkBox, isChecked ->

            if(isChecked){
                showToast("Like added to the image")
            }else{
                showToast("Like removed from the image")
            }

        }

    }

    fun incrementValue(view: View) {
        textView = findViewById(R.id.textView)
        var i = textView.text.toString().toInt()
        textView.setText("${++i}")

    }

    private fun showToast(str:String){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show()
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Posts").child(id)

        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Posts' Data deleted successfully",Toast.LENGTH_LONG).show()

            val intent = Intent(this, MyPostsActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this,"Post deleting error ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvPostId = findViewById(R.id.tvPostId)
        tvPostTitle = findViewById(R.id.tvPostTitle)
        tvPostDesc = findViewById(R.id.tvPostDesc)

        btnEditPost = findViewById(R.id.btnEditPost)
        btnDeletePost = findViewById(R.id.btnDeletePost)
    }

    private fun setValuesToViews(){
        tvPostId.text = intent.getStringExtra("postID")
        tvPostTitle.text = intent.getStringExtra("postTitle")
        tvPostDesc.text = intent.getStringExtra("postDesc")

    }

    private fun openUpdateDialog(
        postID: String,
        postTitle: String,
        //postDesc: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_post_details,null)

        mDialog.setView(mDialogView)

        val etPostTitle = mDialogView.findViewById<EditText>(R.id.etPostTitle)
        val etPostDesc = mDialogView.findViewById<EditText>(R.id.etPostDesc)


        val btnUpdateDate = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etPostTitle.setText(intent.getStringExtra("postTitle").toString())
        etPostDesc.setText(intent.getStringExtra("postDesc").toString())

        mDialog.setTitle("Updating $postTitle Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateDate.setOnClickListener{
            updatePostData(
                postID,
                etPostTitle.text.toString(),
                etPostDesc.text.toString()
            )
            Toast.makeText(applicationContext, "Post Data Updated", Toast.LENGTH_LONG).show()

            //setting updated data to main view
            tvPostTitle.text = etPostTitle.text.toString()
            tvPostDesc.text = etPostDesc.text.toString()


            alertDialog.dismiss()
        }

    }

    private fun updatePostData(
        id:String,
        title:String,
        desc:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Posts").child(id)
        val postInfo = ExpertsPostModel(id,title,desc)
        dbRef.setValue(postInfo)
    }

}