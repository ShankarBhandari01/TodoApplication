package com.shankar.todoapplication.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shankar.todoapplication.R
import com.shankar.todoapplication.ui.TodoActivity

class ReminderWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        try {
            val intent = inputData.getString("intent")
            doReminderWork(intent)
            return Result.success()
        } finally {
            releaseLock()
        }
    }

    private fun doReminderWork(intent: String?) {
        // showNotification(rowId)
    }

    private fun releaseLock() {
        val lock = getLock(applicationContext)
        lock?.release()
    }

    fun getLock(context: Context): PowerManager.WakeLock? {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return powerManager.newWakeLock(
            PowerManager.PARTIAL_WAKE_LOCK,
            ReminderWorker::class.java.simpleName
        )
    }

    private fun showNotification(rowId: Long) {
        // Build the notification
        val notificationIntent = Intent(applicationContext, TodoActivity::class.java)
        notificationIntent.putExtra("KEY_ROWID", rowId)
        // Show the notification
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            rowId.toInt(),
            notificationIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        val CHANNEL_ID = "1.0"
        // Notification channel is required for Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "reminder_channel_id",
                "Reminder Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
//             .setSmallIcon(R.drawable.ic_notification)
//             .setContentTitle(applicationContext.getString(R.string.notify_new_task_title))
//             .setContentText(applicationContext.getString(R.string.notify_new_task_message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()


        notificationManager.notify(rowId.toInt(), notification)
    }
}
