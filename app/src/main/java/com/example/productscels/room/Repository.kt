package com.example.productscels.room

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.productscels.pojos.DetailsItem
import com.example.productscels.retrofit.Details
import com.example.productscels.retrofit.Products
import com.example.productscels.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val mProductsDao: ProductsDao, private val mDetailsDao: DetailsDao) {
    private val service = RetrofitClient.getRetrofitClient()

    val mLiveData = mProductsDao.getAllDataProducts()
    val mLiveDetails = mDetailsDao.getAllDataDetails()

    fun getDataFromProducts() {
        val call =service.getDataFromApi()
        call.enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                when(response.code()) {

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            mProductsDao.insertAllProducts(it)
                        }
                    }
                    in 300..399 -> Log.d("ERROR 300",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Log.e("REPOSITORY",t.message.toString())
            }


        })
    }

    fun getDataFromDetails() {
        val call2 = service.getDataFromDetails()
        call2.enqueue(object : Callback<Details> {
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                when(response.code()) {
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            mDetailsDao.insertAllDetails(it)
                        }
                    }
                    in 300..399 ->Log.d("ERROR 300", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                Log.e("REPOSITORY",t.message.toString())
            }


        })
    }

    fun getOne(mId: Int): LiveData<DetailsItem> {
        return mDetailsDao.getOneDetails(mId)
    }

}
