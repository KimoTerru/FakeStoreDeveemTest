package com.dev.fakestoredeveemtest.domain.usecase.splash

data class SplashUseCase(
    val saveProductsCacheUseCase: SaveProductsCacheUseCase,
    val getProductsNetworkUseCase: GetProductsNetworkUseCase
)