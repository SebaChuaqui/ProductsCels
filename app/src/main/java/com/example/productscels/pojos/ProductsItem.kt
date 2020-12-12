package com.example.productscels.pojos


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products_table")
data class ProductsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)