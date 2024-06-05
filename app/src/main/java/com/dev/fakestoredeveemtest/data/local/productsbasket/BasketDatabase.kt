package com.dev.fakestoredeveemtest.data.local.productsbasket

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.fakestoredeveemtest.data.local.productsbasket.models.ProductBasketEntity

@Database(entities = [ProductBasketEntity::class], version = 1)
abstract class BasketDatabase : RoomDatabase() {
    abstract fun productBasketDao(): ProductBasketDao
}