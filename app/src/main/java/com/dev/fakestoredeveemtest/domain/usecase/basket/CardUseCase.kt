package com.dev.fakestoredeveemtest.domain.usecase.basket

data class CardUseCase(
    val getAllProductBasketUseCase: GetAllProductsBasketUseCase,
    val deleteProductBasketUseCase: DeleteProductBasketUseCase,
    val cleanBasketUseCase: CleanBasketUseCase
)