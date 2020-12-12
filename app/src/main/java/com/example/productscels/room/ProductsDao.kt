package com.example.productscels.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productscels.pojos.ProductsItem
import com.example.productscels.retrofit.Products

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(mProducts: Products)

    @Query("SELECT * FROM products_table")
    fun getAllDataProducts(): LiveData<List<ProductsItem>>

    @Query("SELECT * FROM products_table WHERE id=:mID")
    fun getOneProducts(mID: Int): LiveData<ProductsItem>
}