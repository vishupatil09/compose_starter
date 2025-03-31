package com.compose_demo.vishaldemo.data.network.dto

import com.google.gson.annotations.SerializedName
import java.util.Date


data class DataDto(
    @SerializedName("dataId") val dataId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("date") val date: Date,
    @SerializedName("lastModified") val lastModified: Long
)
