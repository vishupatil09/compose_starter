package com.compose_demo.vishaldemo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Data")
data class DataEntity(
    @PrimaryKey(autoGenerate = false)
    val dataId: Int,
    val title: String,
    val description: String,
    val date: Date,
    val lastModified: Long
)