package com.shankar.todoapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shankar.todoapplication.base.BaseActivity
import com.shankar.todoapplication.databinding.ActivityTodoBinding

class TodoActivity : BaseActivity() {
    private val binding by lazy {
        ActivityTodoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun layout(): View {
        return binding.root
    }
}