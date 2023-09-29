package com.example.cookingapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cookingapp.R
import com.example.cookingapp.databinding.ActivityNotification2Binding

class NotificationActivity : AppCompatActivity() {

    private lateinit var notificationBinding:ActivityNotification2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_notification2)
        notificationBinding=ActivityNotification2Binding.inflate(layoutInflater)
        val view=notificationBinding.root
        setContentView(view)
        setUI()
    }

    private fun setUI()
    {
        val title=intent.getStringExtra("title")
        val message=intent.getStringExtra("message")
        notificationBinding.tvNotificationTitle.text=title
        notificationBinding.tvNotificationDescription.text=message
    }
}