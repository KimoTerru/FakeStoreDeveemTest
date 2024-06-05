package com.dev.fakestoredeveemtest.di.usecase.splash

import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import com.dev.fakestoredeveemtest.domain.repository.ProductRepository
import com.dev.fakestoredeveemtest.domain.usecase.splash.GetProductsNetworkUseCase
import com.dev.fakestoredeveemtest.domain.usecase.splash.SaveProductsCacheUseCase
import com.dev.fakestoredeveemtest.domain.usecase.splash.SplashUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SplashUseCaseModule {

    @Provides
    @Singleton
    fun provideSplashUseCase(
        saveProductsCacheUseCase: SaveProductsCacheUseCase,
        productsNetworkUseCase: GetProductsNetworkUseCase
    ): SplashUseCase {
        return SplashUseCase(
            saveProductsCacheUseCase = saveProductsCacheUseCase,
            getProductsNetworkUseCase = productsNetworkUseCase
        )
    }

    @Provides
    @Singleton
    fun provideSaveProductUseCase(
        productCacheRepository: ProductCacheRepository
    ): SaveProductsCacheUseCase {
        return SaveProductsCacheUseCase(
            productCacheRepository = productCacheRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetProductsNetworkUseCase(
        productRepository: ProductRepository
    ): GetProductsNetworkUseCase {
        return GetProductsNetworkUseCase(
            productRepository = productRepository
        )
    }

}