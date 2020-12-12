package com.example.productscels

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.productscels.pojos.ProductsItem
import com.example.productscels.room.ProductsDao
import com.example.productscels.room.ProductsDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mProductsDao: ProductsDao
    private lateinit var db: ProductsDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProductsDatabase::class.java).build()
        mProductsDao = db.getProductsDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertListElements() = runBlocking {
        //given
        val productsList = listOf(ProductsItem(2,
            "https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/phones/nova7-se/img/mob/huawei-nova7-se-mob.png",
            "Huawei Nova 7 SE 128GB",
            347760))

        // when
        mProductsDao.insertAllProducts(ProductsItem())

        //then
        mProductsDao.getAllDataProducts().observeForever{
            assertThat(it).isNotNull()
            println(it.toString())
            assertThat(it[0].id).isEqualTo("2")

        }
    }

}