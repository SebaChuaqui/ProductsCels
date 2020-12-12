package com.example.productscels.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productscels.pojos.DetailsItem
import com.example.productscels.retrofit.Details

@Dao
interface DetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetails(mProducts: DetailsItem)

    @Query("SELECT * FROM details_table")
    fun getAllDataDetails(): LiveData<List<DetailsItem>>

    @Query("SELECT * FROM details_table WHERE id=:mID")
    fun getOneDetails(mID: Int): LiveData<DetailsItem>

}