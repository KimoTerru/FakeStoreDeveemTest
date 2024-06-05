package com.dev.fakestoredeveemtest.domain.usecase.card

data class CardUseCase(
    val getAllProductBasketUseCase: GetAllProductsBasketUseCase,
    val deleteProductBasketUseCase: DeleteProductBasketUseCase,
    val cleanBasketUseCase: CleanBasketUseCase
)