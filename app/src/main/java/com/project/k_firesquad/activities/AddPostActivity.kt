package com.project.k_firesquad.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.k_firesquad.R
import com.project.k_firesquad.models.ExpertsPostModel

class AddPostActivity : AppCompatActivity() {

    private lateinit var postTitle: EditText
    private lateinit var postDesc: EditText
    private lateinit var btnAddPost: Button

    private lateinit var dbRef: DatabaseReference

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: NotificationCompat.Builder
    private val channelId = "my_channel_01"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post_form)

        postTitle = findViewById(R.id.enterPostTitle)
        postDesc = findViewById(R.id.enterPostDes)
        btnAddPost = findViewById(R.id.btnAddPost)

        dbRef = FirebaseDatabase.getInstance().getReference("Posts")

        btnAddPost.setOnClickListener(){
            savePostsData()
            showNotification()
        }

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,
                description,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun showNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("AGRO")
            .setContentText("Post added successfully")
            .setSmallIcon(R.drawable.notification1)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        notificationManager.notify(1234, builder.build())
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
