package com.example.productscels

import com.example.productscels.pojos.ProductsItem
import com.example.productscels.retrofit.ApiProducts
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mApiProducts : ApiProducts

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val retro = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiProducts =  retro.create(com.example.productscels.Retrofit.mApiProducts::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getAllDrugstore_happy_case() = runBlocking {
        //given
        val mresultList = listOf<ProductsItem>(
            ProductsItem(2
                "https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/phones/nova7-se/img/mob/huawei-nova7-se-mob.png",
                "Huawei Nova 7 SE 128GB",
                347760)
        )
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mresultList)))

        //when
        val result = mApiProducts.getDataFromApi()
        //then
        assertThat(result).isNotNull()
        assertThat(result.isSuccessful).isTrue()
        val message = result.body()
        assertThat(message).hasSize(1)
        println(message.toString())
        assertThat(message?.get(0)?.codigo?.contains("PZ-01")).isTrue()
        val request = mMockWebServer.takeRequest()
        Truth.assertThat(request.path).isEqualTo("/products")
    }