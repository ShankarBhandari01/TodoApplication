package com.shankar.todoapplication.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shankar.todoapplication.type_convertor.DrawableTypeConvertor

@Entity(tableName = "Tbl_category")
data class CategoryModel(
    var image: Int? = 0,
    var title: String? = null,
    var taskNumber: Int? = 0,
    var backgroundColor: Int? = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}