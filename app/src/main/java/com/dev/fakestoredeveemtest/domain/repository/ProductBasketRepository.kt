package com.dev.fakestoredeveemtest.domain.repository

import com.dev.fakestoredeveemtest.domain.models.ProductModel

interface ProductBasketRepository {
    suspend fun getAllBasketProduct(): List<ProductModel>
    suspend fun saveBasketProduct(product: ProductModel)
    suspend fun deleteBasketProduct(product: ProductModel)
    suspend fun cleanBasket()
}