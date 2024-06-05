package com.dev.fakestoredeveemtest.domain.usecase.home

import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import com.dev.fakestoredeveemtest.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsNetworkUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun invoke(): List<ProductModel> {
        return try {
            productRepository.getAllProducts()
        } catch (e: Exception) {
            emptyList()
        }
    }
}