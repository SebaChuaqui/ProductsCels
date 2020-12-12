package com.example.productscels.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.productscels.pojos.DetailsItem
import com.example.productscels.pojos.ProductsItem
import com.example.productscels.room.ProductsDatabase
import com.example.productscels.room.Repository


class MyViewModel( application: Application): AndroidViewModel(application) {

    private  val mRepository : Repository

    val mAllProducts : LiveData<List<ProductsItem>>

    val mAllDetails : LiveData<List<DetailsItem>>

    init {
        val mProductsDao = ProductsDatabase.getDataBase(application).getProductsDao()
        val mDetailsDao = ProductsDatabase.getDataBase(application).getDetailsDao()

        mRepository = Repository(mProductsDao, mDetailsDao)

        mAllProducts = mRepository.mLiveData
        mAllDetails = mRepository.mLiveDetails

        mRepository.getDataFromProducts()
        mRepository.getDataFromDetails()
    }

    fun getOneId(mID: Int): LiveData<DetailsItem> {
        return mRepository.getOneDetails(mID)
    }
}