package com.example.desighkotlin.repository

import com.example.desighkotlin.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = BuildConfig.NASA_API_KEY

interface PODAPI {
    @GET("planetary/apod")

    fun getPOD(@Query(API_KEY) apiKey : String) : Call<PODServerResponseData>
}