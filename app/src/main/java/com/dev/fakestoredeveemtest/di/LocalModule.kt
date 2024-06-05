package com.dev.fakestoredeveemtest.di

import android.content.Context
import androidx.room.Room
import com.dev.fakestoredeveemtest.data.local.productcache.ProductCacheDao
import com.dev.fakestoredeveemtest.data.local.productcache.ProductCacheDatabase
import com.dev.fakestoredeveemtest.data.local.productsbasket.BasketDatabase
import com.dev.fakestoredeveemtest.data.local.productsbasket.ProductBasketDao
import com.dev.fakestoredeveemtest.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    fun provideProductCacheDao(productCacheDatabase: ProductCacheDatabase): ProductCacheDao {
        return productCacheDatabase.productCacheDao()
    }

    @Provides
    @Singleton
    fun provideProductCacheDatabase(@ApplicationContext appContext: Context): ProductCacheDatabase {
        return Room.databaseBuilder(
            appContext, ProductCacheDatabase::class.java, AppConstants.PRODUCTS_CACHE_NAME
        ).build()
    }

    @Provides
    fun provideBasketDao(basketDatabase: BasketDatabase): ProductBasketDao {
        return basketDatabase.productBasketDao()
    }

    @Provides
    @Singleton
    fun provideBasketDatabase(@ApplicationContext appContext: Context): BasketDatabase {
        return Room.databaseBuilder(
            appContext, BasketDatabase::class.java, AppConstants.PRODUCTS_BASKET_NAME
        ).build()
    }
}