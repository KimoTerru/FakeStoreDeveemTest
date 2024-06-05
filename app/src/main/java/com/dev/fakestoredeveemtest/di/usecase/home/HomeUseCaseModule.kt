package com.dev.fakestoredeveemtest.di.usecase.home

import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import com.dev.fakestoredeveemtest.domain.usecase.home.GetProductByIdCacheUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.GetProductsByCategoriesCacheUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.GetProductsCacheUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.HomeUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.SaveProductToBasketUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeUseCaseModule {

    @Provides
    @Singleton
    fun provideHomeUseCase(
        getProductsCacheUseCase: GetProductsCacheUseCase,
        getProductByIdCacheUseCase: GetProductByIdCacheUseCase,
        getProductsByCategoriesCacheUseCase: GetProductsByCategoriesCacheUseCase,
        saveProductToBasketUseCase: SaveProductToBasketUseCase
    ): HomeUseCase {
        return HomeUseCase(
            getProductsCacheUseCase = getProductsCacheUseCase,
            getProductByIdCacheUseCase = getProductByIdCacheUseCase,
            getProductsByCategoriesCacheUseCase = getProductsByCategoriesCacheUseCase,
            saveProductToBasketUseCase = saveProductToBasketUseCase
        )
    }

    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(
        productCacheRepository: ProductCacheRepository
    ): GetProductsCacheUseCase {
        return GetProductsCacheUseCase(
            productCacheRepository = productCacheRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetProductByIdUseCase(
        productCacheRepository: ProductCacheRepository
    ): GetProductByIdCacheUseCase {
        return GetProductByIdCacheUseCase(
            productCacheRepository = productCacheRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetProductsByCategoriesUseCase(
        repository: ProductCacheRepository
    ): GetProductsByCategoriesCacheUseCase {
        return GetProductsByCategoriesCacheUseCase(
            productCacheRepository = repository
        )
    }

    @Provides
    @Singleton
    fun provideSaveProductToBasketUseCase(
        repository: ProductBasketRepository
    ): SaveProductToBasketUseCase {
        return SaveProductToBasketUseCase(
            productBasketRepository = repository
        )
    }

}