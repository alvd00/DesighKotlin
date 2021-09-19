package com.example.desighkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desighkotlin.BuildConfig
import com.example.desighkotlin.repository.PODRetrofitImpl
import com.example.desighkotlin.repository.PODServerResponseData
import com.example.desighkotlin.repository.SolarFlareResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PODViewModel(private val liveDataToObserve: MutableLiveData<PODData> = MutableLiveData(),
                   private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()):ViewModel() {
    fun getLiveData():LiveData<PODData>{
        return liveDataToObserve
    }

    fun sendServerRequest(){
        liveDataToObserve.postValue(PODData.Loading())
        val apiKey = BuildConfig.NASA_API_KEY
        if(apiKey.isBlank()){
            //
        }else{
            retrofitImpl.getPictureOfTheDay(apiKey,PODCallback)
            retrofitImpl.getSolarFlareToday(apiKey,solarFlareCallback,"2021-09-01")
        }
    }

    val PODCallback  = object : Callback<PODServerResponseData>{
        override fun onResponse(
            call: Call<PODServerResponseData>,
            response: Response<PODServerResponseData>
        ) {
            if(response.isSuccessful && response.body()!=null){
                liveDataToObserve.postValue(PODData.Success(response.body() as PODServerResponseData)) // FIXME костыль
            }else{
                // TODO HW
            }
        }

        override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
            // TODO HW
        }
    }

    val solarFlareCallback  = object : Callback<List<SolarFlareResponseData>>{
        override fun onResponse(
            call: Call<List<SolarFlareResponseData>>,
            response: Response<List<SolarFlareResponseData>>
        ) {
            if(response.isSuccessful && response.body()!=null){
            }else{
                // TODO HW
            }
        }

        override fun onFailure(call: Call<List<SolarFlareResponseData>>, t: Throwable) {
            liveDataToObserve.postValue(PODData.Error(t))
        }
    }
}