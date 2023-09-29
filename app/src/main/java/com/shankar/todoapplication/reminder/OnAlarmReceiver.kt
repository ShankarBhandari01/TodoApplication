package com.shankar.todoapplication.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.shankar.todoapplication.service.ReminderWorker

class OnAlarmReceiver : BroadcastReceiver() {
    private val TAG = OnAlarmReceiver::class.java.canonicalName

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Received wake up from alarm manager.")

        // Extract the row ID from the intent
        val rowId = intent?.extras?.getLong(RemindersDbAdapter.KEY_ROWID) ?: -1L

        if (rowId != -1L) {
            // Pass the row ID as input data
            val inputData = Data.Builder()
                .putLong("KEY_ROWID", rowId)
                .build()

            // Create a OneTimeWorkRequest with the ReminderWorker
            val reminderWorkRequest = OneTimeWorkRequest.Builder(ReminderWorker::class.java)
                .setInputData(inputData)
                .build()

            // Enqueue the work request
            WorkManager.getInstance(context!!).enqueue(reminderWorkRequest)
        } else {
            Log.e(TAG, "Invalid row ID in the intent")
        }
    }
}
