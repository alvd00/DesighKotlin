package com.example.desighkotlin.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback

class PODRetrofitImpl {
    private val baseUrl = "https://api.nasa.gov/"

    private val api by lazy {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            //.client(createOkHttpClient(PODInterceptor()))
            .build().create(RetrofitAPI::class.java)
    }

    fun getPictureOfTheDay(apiKey: String, podCallback: Callback<PODServerResponseData>) {
        api.getPictureOfTheDay(apiKey).enqueue(podCallback)
    }

    fun getSolarFlareToday(apiKey: String, podCallback: Callback<List<SolarFlareResponseData>>,startDate:String="2021-09-07") {
        api.getSolarFlareToday(apiKey,startDate).enqueue(podCallback)
    }
}