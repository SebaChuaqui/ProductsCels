package com.example.productscels.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productscels.pojos.ProductsItem

private const val DATA_BESE_NAME= "Productos_db"
@Database(entities=[ProductsItem::class], version=1 )
abstract class ProductsDB : RoomDatabase() {

    //Método para Dao
    abstract fun getproductosDao(): ProductsDao

    companion object {  //permite acceder a el sin necesidad de crear el objeto de la clase, nombre clase + punto y acceso a lo que está en el companen objet
        @Volatile
        private var INSTANCE:ProductsDB? = null
        fun getDataBase(context: Context): ProductsDB {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    ProductsDB::class.java,
                    DATA_BESE_NAME)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}