package com.shankar.todoapplication.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*


class ReminderManager(private var context: Context) {
    private var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun setReminder(taskId: Long, time: Calendar) {
        val i = Intent(context, OnAlarmReceiver::class.java)
        i.putExtra("KEY_ROWID", taskId)
        val pi = PendingIntent.getBroadcast(
            context, 0, i,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.set(AlarmManager.RTC_WAKEUP, time.timeInMillis, pi)
    }
}