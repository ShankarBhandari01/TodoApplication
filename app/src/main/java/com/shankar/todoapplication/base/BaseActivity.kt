package com.shankar.todoapplication.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shankar.todoapplication.database.DatabaseManager
import kotlinx.coroutines.*

abstract class BaseActivity : AppCompatActivity() {
    val database by lazy {
        DatabaseManager.getDatabase(applicationContext)
    }
    var bottomSheetDialog: BottomSheetDialog? = null
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

    inline fun <reified T : ViewBinding> attachBaseBottomSheet(): T {
        if (bottomSheetDialog != null && bottomSheetDialog?.isShowing == true) {
            bottomSheetDialog?.dismiss()
        }
        bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = T::class.java.getMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as T
        bottomSheetDialog?.setContentView(bottomSheetBinding.root)
        return bottomSheetBinding
    }


    open fun showBottomSheet() {
        if (bottomSheetDialog != null) {
            bottomSheetDialog?.show()
        }
    }

    open fun hideBottomSheet() {
        if (bottomSheetDialog != null) {
            bottomSheetDialog?.hide()
        }
    }

}

