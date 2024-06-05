package com.dev.fakestoredeveemtest.domain.usecase.basket

import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import javax.inject.Inject

class CleanBasketUseCase @Inject constructor(
    private val repository: ProductBasketRepository
) {
    suspend fun invoke() {
        return try {
            repository.cleanBasket()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}