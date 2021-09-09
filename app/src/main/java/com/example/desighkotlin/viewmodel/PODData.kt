package com.example.desighkotlin.viewmodel

import com.example.desighkotlin.repository.PODServerResponseData

sealed class PODData{
    data class Success(val serverResponseData: PODServerResponseData) : PODData()
    data class Error(val error: Throwable) : PODData()
    class Loading() : PODData() //FIXME mina2

}
