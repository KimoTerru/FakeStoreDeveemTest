package com.dev.fakestoredeveemtest.domain.repository

import com.dev.fakestoredeveemtest.domain.models.ProductModel

interface ProductCacheRepository {
    suspend fun getAllProducts(): List<ProductModel>
    suspend fun getProductById(id: Int): ProductModel
    suspend fun getProductsByCategories(category: String): List<ProductModel>
    suspend fun saveProducts(productList: List<ProductModel>)
    suspend fun clearAllProducts()

}