package com.dev.fakestoredeveemtest.domain.usecase.home

data class HomeUseCase(
    val getProductsCacheUseCase: GetProductsCacheUseCase,
    val getProductByIdCacheUseCase: GetProductByIdCacheUseCase,
    val getProductsByCategoriesCacheUseCase: GetProductsByCategoriesCacheUseCase,
    val saveProductToBasketUseCase: SaveProductToBasketUseCase
)