package com.example.desighkotlin.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "api key"

interface PODAPI {
    @GET("planetary/apod")

    fun getPOD(@Query(API_KEY) apiKey : String) : Call<PODServerResponseData>
}