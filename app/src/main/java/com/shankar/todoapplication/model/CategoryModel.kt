package com.shankar.todoapplication.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tbl_category")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var image: Drawable? = null,
    var title: String? = null,
    var taskNumber: Int? = 0,
    var backgroundColor: Int? = 0
)