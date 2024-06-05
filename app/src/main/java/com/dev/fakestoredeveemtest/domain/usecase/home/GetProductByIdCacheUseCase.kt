package com.dev.fakestoredeveemtest.domain.usecase.home

import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import javax.inject.Inject

class GetProductByIdCacheUseCase @Inject constructor(
    private val productCacheRepository: ProductCacheRepository
) {
    suspend fun invoke(id: Int): ProductModel {
        return try {
            productCacheRepository.getProductById(id)
        } catch (e: Exception) {
            throw e
        }
    }
}