package com.compose_demo.vishaldemo.data.network.api

import com.compose_demo.vishaldemo.data.network.dto.DataDto
import retrofit2.http.Body
import retrofit2.http.POST

interface Upsert {

    @POST("upsert/data")
    suspend fun upsertData(@Body data: DataDto)

}