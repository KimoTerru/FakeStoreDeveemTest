package com.dev.fakestoredeveemtest.domain.usecase.splash

import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import javax.inject.Inject

class SaveProductsCacheUseCase @Inject constructor(
    private val productCacheRepository: ProductCacheRepository
) {
    suspend fun invoke(productList: List<ProductModel>) {
        return try {
            productCacheRepository.saveProducts(productList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}