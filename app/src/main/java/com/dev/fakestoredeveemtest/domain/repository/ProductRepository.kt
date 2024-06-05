package com.dev.fakestoredeveemtest.domain.repository

import com.dev.fakestoredeveemtest.domain.models.ProductModel

interface ProductRepository {
    suspend fun getAllProducts(): List<ProductModel>
}