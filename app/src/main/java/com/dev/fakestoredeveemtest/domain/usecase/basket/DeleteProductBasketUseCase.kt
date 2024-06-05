package com.dev.fakestoredeveemtest.domain.usecase.basket

import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import javax.inject.Inject

class DeleteProductBasketUseCase @Inject constructor(
    private val productBasketRepository: ProductBasketRepository
) {
    suspend fun invoke(productModel: ProductModel) {
        return try {
            productBasketRepository.deleteBasketProduct(productModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}