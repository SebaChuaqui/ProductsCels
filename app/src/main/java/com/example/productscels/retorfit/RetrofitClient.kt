package com.example.productscels.retorfit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {

        private const val URL_BASE = " https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"
        fun getRetrofitClient() : ApiProducts {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(ApiProducts::class.java)
        }
    }


}