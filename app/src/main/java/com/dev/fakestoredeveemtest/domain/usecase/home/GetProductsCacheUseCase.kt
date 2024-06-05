package com.dev.fakestoredeveemtest.domain.usecase.home

import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import javax.inject.Inject

class GetProductsCacheUseCase @Inject constructor(
    private val productCacheRepository: ProductCacheRepository
) {
    suspend fun invoke(): List<ProductModel> {
        return productCacheRepository.getAllProducts()
    }
}