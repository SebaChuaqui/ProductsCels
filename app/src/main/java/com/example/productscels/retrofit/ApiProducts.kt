package com.example.productscels.retrofit

import com.example.productscels.pojos.details
import com.example.productscels.pojos.products
import retrofit2.Call
import retrofit2.http.GET

interface ApiProducts {

    @GET("products")
    fun getDataFromApi(): Call<products>


    @GET("details/{id}")
    fun getDataFromDetails(): Call<details>
}