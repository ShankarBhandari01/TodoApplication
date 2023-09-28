package com.shankar.todoapplication.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shankar.todoapplication.database.AppDatabase
import com.shankar.todoapplication.database.BaseDao
import com.shankar.todoapplication.database.DatabaseManager
import com.shankar.todoapplication.repository.RoomDataBaseRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity() {
    val database by lazy {
        DatabaseManager.getDatabase(applicationContext)
    }
    val applicationScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.Main)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.layout())
    }

    abstract fun layout(): View

    open fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}

