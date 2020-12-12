package com.example.productscels.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productscels.pojos.DetailsItem
import com.example.productscels.pojos.ProductsItem

private const val DATA_BASE_NAME = "products_db"

@Database(entities = [ProductsItem::class, DetailsItem::class],version = 1)

abstract class ProductsDatabase : RoomDatabase() {
    abstract fun getProductsDao() : ProductsDao
    abstract fun getDetailsDao() : DetailsDao

    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        fun getDataBase(context: Context): ProductsDatabase {

            val tempInterface = INSTANCE
            if(tempInterface != null) {
                return tempInterface
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    ProductsDatabase::class.java,
                    DATA_BASE_NAME)
                    .build()

                INSTANCE = instance
                return instance
            }

        }
    }
}
