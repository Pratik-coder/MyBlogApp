package com.example.cookingapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.cookingapp.R
import com.example.cookingapp.activity.NotificationActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class BlogAppFirebaseMessagingService:FirebaseMessagingService()
{
    override fun onMessageReceived(remoteMessage: RemoteMessage)
    {
        if (remoteMessage.data.isNotEmpty())
        {
            val data=remoteMessage.data

            val title=data["title"]
            val message=data["message"]
            val intent=Intent(this,NotificationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("title",title)
            intent.putExtra("message",message)

            val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)
            val notificationBuilder=NotificationCompat.Builder(this,"channel_id")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0,notificationBuilder.build())
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}