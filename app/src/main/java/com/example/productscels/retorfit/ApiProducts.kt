package com.example.productscels.retorfit

import com.example.productscels.pojos.ProductsItem
import details
import retrofit2.http.GET

interface ApiProducts {

    @GET("products")
    fun getProductsFromApi(): retrofit2.Call<List<ProductsItem>>

    @GET("details")
    fun getDetailsFromApi(): retrofit2.Call<List<details>>
}