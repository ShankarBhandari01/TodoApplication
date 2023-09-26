package com.shankar.todoapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.shankar.todoapplication.R
import com.shankar.todoapplication.base.BaseActivity
import com.shankar.todoapplication.databinding.ActivityTodoBinding
import com.shankar.todoapplication.model.CategoryModel
import com.shankar.todoapplication.ui.adapter.CategoryAdaptor

class TodoActivity : BaseActivity() {
    private val binding by lazy {
        ActivityTodoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        setUpUser()
    }

    private fun setUpUser() {
        binding.profileImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.profile))
        binding.tvName.text = "Shankar Bhandari"
    }

    override fun layout(): View {
        return binding.root
    }

    override fun initRecyclerView() {
        val list: ArrayList<CategoryModel> = ArrayList()
        val categoryModel = CategoryModel()
        categoryModel.image = ContextCompat.getDrawable(this, R.drawable.painting)
        categoryModel.title = "Design"
        categoryModel.taskNumber = 5
        categoryModel.backgroundColor = ContextCompat.getColor(this, R.color.design_card)


        val categoryModelLearning = CategoryModel()
        categoryModelLearning.image = ContextCompat.getDrawable(this, R.drawable.learing)
        categoryModelLearning.title = "Learning"
        categoryModelLearning.taskNumber = 3
        categoryModelLearning.backgroundColor = ContextCompat.getColor(this, R.color.learning_card)


        val categoryModelMeeting = CategoryModel()
        categoryModelMeeting.image = ContextCompat.getDrawable(this, R.drawable.meeting)
        categoryModelMeeting.title = "Meeting"
        categoryModelMeeting.taskNumber = 1
        categoryModelMeeting.backgroundColor = ContextCompat.getColor(this, R.color.meeting_card)

        list.add(categoryModel)
        list.add(categoryModelLearning)
        list.add(categoryModelMeeting)
        val categoryAdaptor = CategoryAdaptor(list, this)
        binding.recview.adapter = categoryAdaptor
    }

}