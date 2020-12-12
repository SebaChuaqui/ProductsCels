package com.example.productscels.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productscels.pojos.ProductsItem

@Dao
interface ProductsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertAllProducts(mList: List<ProductsItem>)

    @Query("SELECT * FROM products_table")
    fun getAllProducts(): LiveData<List<ProductsItem>>

    @Query("SELECT * FROM products_table WHERE id=:mid")
    fun getOneProductsByID(mid: Int): LiveData<List<ProductsItem>>

}