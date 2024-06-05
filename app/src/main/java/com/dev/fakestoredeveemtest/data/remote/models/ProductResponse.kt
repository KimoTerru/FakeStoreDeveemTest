package com.dev.fakestoredeveemtest.data.remote.models


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("title")
    val title: String? = null
)