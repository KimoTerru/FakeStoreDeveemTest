package com.dev.fakestoredeveemtest.domain.usecase.basket

import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import javax.inject.Inject

class GetAllProductsBasketUseCase @Inject constructor(
    private val basketRepository: ProductBasketRepository
) {
    suspend fun invoke(): List<ProductModel> {
        return try {
            basketRepository.getAllBasketProduct()
        } catch (e: Exception) {
            emptyList()
        }
    }
}