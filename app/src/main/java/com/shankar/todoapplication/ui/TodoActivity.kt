package com.shankar.todoapplication.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.shankar.todoapplication.R
import com.shankar.todoapplication.base.BaseActivity
import com.shankar.todoapplication.base.UiState
import com.shankar.todoapplication.base.ViewModelFactory
import com.shankar.todoapplication.databinding.ActivityTodoBinding
import com.shankar.todoapplication.databinding.BottomSheetBinding
import com.shankar.todoapplication.model.CategoryModel
import com.shankar.todoapplication.repository.RoomDataBaseRepository
import com.shankar.todoapplication.ui.adapter.CategoryAdaptor
import com.shankar.todoapplication.viewmodels.TodoViewModels
import kotlinx.coroutines.launch

class TodoActivity : BaseActivity() {
    private val binding by lazy {
        ActivityTodoBinding.inflate(layoutInflater)
    }
    lateinit var bottomSheetBinding: BottomSheetBinding
    private lateinit var viewModel: TodoViewModels

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                RoomDataBaseRepository(database.categoryDao())
            )
        )[TodoViewModels::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        initRecyclerView()
        setUpUser()
        retrieveDataFromDatabase()
        bottomSheetBinding = attachBaseBottomSheet()

    }

    private fun setUpUser() {
        binding.profileImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.profile))
        binding.tvName.text = "Shankar Bhandari"
    }

    override fun layout(): View {
        return binding.root
    }

    private fun initRecyclerView() {
        val list: ArrayList<CategoryModel> = ArrayList()
        val categoryModel = CategoryModel()
        categoryModel.image = R.drawable.painting
        categoryModel.title = "Design"
        categoryModel.taskNumber = 5
        categoryModel.backgroundColor = ContextCompat.getColor(this, R.color.design_card)


        val categoryModelLearning = CategoryModel()
        categoryModelLearning.image = R.drawable.learing
        categoryModelLearning.title = "Learning"
        categoryModelLearning.taskNumber = 3
        categoryModelLearning.backgroundColor = ContextCompat.getColor(this, R.color.learning_card)


        val categoryModelMeeting = CategoryModel()
        categoryModelMeeting.image = R.drawable.meeting
        categoryModelMeeting.title = "Meeting"
        categoryModelMeeting.taskNumber = 1
        categoryModelMeeting.backgroundColor = ContextCompat.getColor(this, R.color.meeting_card)

        list.add(categoryModel)
        list.add(categoryModelLearning)
        list.add(categoryModelMeeting)

        // insert operation
        viewModel.insertCategory(list)
    }

    private fun retrieveDataFromDatabase() {
        viewModel.getCategory()
        lifecycleScope.launch {
            viewModel.getCategoryList.collect { category ->
                when (category) {
                    is UiState.Loading -> {
                        showToast("Loading")
                    }

                    is UiState.Success -> {
                        val categoryAdaptor =
                            category.data?.let { CategoryAdaptor(it, this@TodoActivity) }
                        binding.recview.adapter = categoryAdaptor
                    }

                    is UiState.Error -> {
                        showToast(category.message!!)
                    }

                    else -> {}
                }
            }
        }
    }
}