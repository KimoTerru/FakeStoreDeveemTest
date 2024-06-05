package com.dev.fakestoredeveemtest.domain.usecase.home

import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import javax.inject.Inject

class SaveProductToBasketUseCase @Inject constructor(
    private val productBasketRepository: ProductBasketRepository
) {
    suspend fun invoke(productId: ProductModel) {
        return try {
            productBasketRepository.saveBasketProduct(productId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}