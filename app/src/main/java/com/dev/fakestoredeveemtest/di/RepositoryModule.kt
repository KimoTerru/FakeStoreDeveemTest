package com.dev.fakestoredeveemtest.di

import com.dev.fakestoredeveemtest.data.repository.ProductBasketRepositoryImpl
import com.dev.fakestoredeveemtest.data.repository.ProductCacheRepositoryImpl
import com.dev.fakestoredeveemtest.data.repository.ProductRepositoryImpl
import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import com.dev.fakestoredeveemtest.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repository: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindProductCacheRepository(
        repository: ProductCacheRepositoryImpl
    ): ProductCacheRepository

    @Binds
    @Singleton
    abstract fun bindProductBasketRepository(
        repository: ProductBasketRepositoryImpl
    ): ProductBasketRepository

}