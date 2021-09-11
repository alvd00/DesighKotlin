package com.example.desighkotlin.viewmodel

import android.content.Context
import android.util.Log
import android.view.ContextMenu
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desighkotlin.BuildConfig
import com.example.desighkotlin.repository.PODRetrofitImpl
import com.example.desighkotlin.repository.PODServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class PODViewModel(private val liveDataToObserve:MutableLiveData<PODData> = MutableLiveData(),

        private val retrofitImpl : PODRetrofitImpl = PODRetrofitImpl()) : ViewModel() {
            fun getLiveData(): LiveData<PODData>{
                return liveDataToObserve
            }

    fun sendServerRequest(){
        liveDataToObserve.postValue(PODData.Loading())
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()){

        }
        else{
            retrofitImpl.getRetrofitImpl().getPOD(apiKey).enqueue(
                object : Callback<PODServerResponseData>{
                    override fun onResponse(
                        call: Call<PODServerResponseData>,
                        response: Response<PODServerResponseData>
                    ) {
                        if(response.isSuccessful && response.body()!=null){
                            liveDataToObserve.postValue(PODData.Success(response.body() as PODServerResponseData)) // FIXME костыль
                        }
                        else{

                            Log.d("TAGGG", "EEEEER")
                             //HW
                        }
                    }

                    override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    //TODO HW2
                    }
                }
            )
        }
    }
}