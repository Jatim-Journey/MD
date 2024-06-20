package com.example.javajourney.data.api

import com.example.javajourney.data.response.GetPlace
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("places")
    fun getPlaces(): Call<GetPlace>

    @GET("choice")
    fun getChoice(@Query("user_id") userId: Int): Call<GetPlace>
}

