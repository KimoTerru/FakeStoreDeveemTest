package com.dev.fakestoredeveemtest.di.usecase.basket

import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import com.dev.fakestoredeveemtest.domain.usecase.card.CardUseCase
import com.dev.fakestoredeveemtest.domain.usecase.card.CleanBasketUseCase
import com.dev.fakestoredeveemtest.domain.usecase.card.DeleteProductBasketUseCase
import com.dev.fakestoredeveemtest.domain.usecase.card.GetAllProductsBasketUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CardUseCaseModule {

    @Provides
    @Singleton
    fun provideHomeUseCase(
        getBasketUseCase: GetAllProductsBasketUseCase,
        deleteProductBasketUseCase: DeleteProductBasketUseCase,
        cleanBasketUseCase: CleanBasketUseCase
    ): CardUseCase {
        return CardUseCase(
            getAllProductBasketUseCase = getBasketUseCase,
            deleteProductBasketUseCase = deleteProductBasketUseCase,
            cleanBasketUseCase = cleanBasketUseCase
        )
    }

    @Provides
    @Singleton
    fun provideGetAllProductsBasketUseCase(
        repository: ProductBasketRepository
    ): GetAllProductsBasketUseCase {
        return GetAllProductsBasketUseCase(
            basketRepository = repository
        )
    }

    @Provides
    @Singleton
    fun provideDeleteProductBasketUseCase(
        repository: ProductBasketRepository
    ): DeleteProductBasketUseCase {
        return DeleteProductBasketUseCase(
            productBasketRepository = repository
        )
    }

    @Provides
    @Singleton
    fun provideCleanBasketUseCase(
        repository: ProductBasketRepository
    ): CleanBasketUseCase {
        return CleanBasketUseCase(
            repository = repository
        )
    }
}