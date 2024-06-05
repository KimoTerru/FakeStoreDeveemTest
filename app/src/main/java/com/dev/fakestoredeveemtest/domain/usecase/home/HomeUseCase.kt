package com.dev.fakestoredeveemtest.domain.usecase.home

data class HomeUseCase(
    val getProductsCacheUseCase: GetProductsCacheUseCase,
    val getProductByIdUseCase: GetProductByIdUseCase,
    val getProductsByCategoriesUseCase: GetProductsByCategoriesUseCase,
    val saveProductsCacheUseCase: SaveProductsCacheUseCase,

    val getProductsNetworkUseCase: GetProductsNetworkUseCase,

    val getDownloadedStateProducts: GetDownloadedStateProducts,
    val saveDownloadedStateProducts: SaveDownloadedStateProducts,

    val saveProductToBasketUseCase: SaveProductToBasketUseCase
)