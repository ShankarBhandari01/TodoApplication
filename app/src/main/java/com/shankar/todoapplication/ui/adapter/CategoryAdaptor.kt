package com.shankar.todoapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shankar.todoapplication.databinding.CategoryLayoutBinding
import com.shankar.todoapplication.model.CategoryModel
import com.shankar.todoapplication.ui.TodoActivity

class CategoryAdaptor(var data: List<CategoryModel>, var context: TodoActivity) :
    RecyclerView.Adapter<CategoryAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdaptor.ViewHolder {
        return ViewHolder(
            CategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryAdaptor.ViewHolder, position: Int) {
        holder.binding.category = data[position]
        holder.binding.icon.setImageDrawable(data[position].image?.let {
            ContextCompat.getDrawable(
                context,
                it
            )
        })
        holder.binding.imgbtnAdd.setOnClickListener {
            context.showToast("Add buttons click")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}