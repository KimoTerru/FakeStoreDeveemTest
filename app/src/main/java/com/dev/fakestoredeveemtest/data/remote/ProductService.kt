package com.dev.fakestoredeveemtest.data.remote

import com.dev.fakestoredeveemtest.data.remote.models.ProductResponse
import com.dev.fakestoredeveemtest.util.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET(AppConstants.GET_PRODUCTS_NETWORK)
    suspend fun getAllProducts(@Query("limit") limit: Int): List<ProductResponse>

}