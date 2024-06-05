package com.dev.fakestoredeveemtest.di.usecase.home

import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import com.dev.fakestoredeveemtest.domain.repository.ProductRepository
import com.dev.fakestoredeveemtest.domain.repository.ProductSharedRepository
import com.dev.fakestoredeveemtest.domain.usecase.home.GetDownloadedStateProducts
import com.dev.fakestoredeveemtest.domain.usecase.home.GetProductByIdUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.GetProductsByCategoriesUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.GetProductsCacheUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.GetProductsNetworkUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.HomeUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.SaveDownloadedStateProducts
import com.dev.fakestoredeveemtest.domain.usecase.home.SaveProductToBasketUseCase
import com.dev.fakestoredeveemtest.domain.usecase.home.SaveProductsCacheUseCase
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
        getProductByIdUseCase: GetProductByIdUseCase,
        getProductsByCategoriesUseCase: GetProductsByCategoriesUseCase,
        saveProductsCacheUseCase: SaveProductsCacheUseCase,
        getProductsNetworkUseCase: GetProductsNetworkUseCase,
        getDownloadedStateProducts: GetDownloadedStateProducts,
        saveDownloadedStateProducts: SaveDownloadedStateProducts,
        saveProductToBasketUseCase: SaveProductToBasketUseCase
    ): HomeUseCase {
        return HomeUseCase(
            getProductsCacheUseCase = getProductsCacheUseCase,
            getProductByIdUseCase = getProductByIdUseCase,
            saveProductsCacheUseCase = saveProductsCacheUseCase,

            getProductsNetworkUseCase = getProductsNetworkUseCase,

            getDownloadedStateProducts = getDownloadedStateProducts,
            saveDownloadedStateProducts = saveDownloadedStateProducts,
            getProductsByCategoriesUseCase = getProductsByCategoriesUseCase,
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
    ): GetProductByIdUseCase {
        return GetProductByIdUseCase(
            productCacheRepository = productCacheRepository
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

    @Provides
    @Singleton
    fun provideGetDownloadedStateProducts(
        repository: ProductSharedRepository
    ): GetDownloadedStateProducts {
        return GetDownloadedStateProducts(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun provideSaveDownloadedStateProducts(
        repository: ProductSharedRepository
    ): SaveDownloadedStateProducts {
        return SaveDownloadedStateProducts(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun provideGetProductsByCategoriesUseCase(
        repository: ProductCacheRepository
    ): GetProductsByCategoriesUseCase {
        return GetProductsByCategoriesUseCase(
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