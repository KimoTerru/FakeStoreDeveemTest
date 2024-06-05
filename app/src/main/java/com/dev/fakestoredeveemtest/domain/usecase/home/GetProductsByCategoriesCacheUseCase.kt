package com.dev.fakestoredeveemtest.domain.usecase.home

import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import javax.inject.Inject

class GetProductsByCategoriesCacheUseCase @Inject constructor(
    private val productCacheRepository: ProductCacheRepository
) {
    suspend fun invoke(category: String?): List<ProductModel> {
        return if (category != null) {
            productCacheRepository.getProductsByCategories(category)
        } else {
            emptyList()
        }
    }
}