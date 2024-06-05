package com.dev.fakestoredeveemtest.data.local.productcache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.fakestoredeveemtest.data.local.productcache.models.ProductCacheEntity

@Database(entities = [ProductCacheEntity::class], version = 1)
abstract class ProductCacheDatabase: RoomDatabase() {
    abstract fun productCacheDao(): ProductCacheDao
}