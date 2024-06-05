package com.dev.fakestoredeveemtest.data.local.productcache.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.fakestoredeveemtest.util.AppConstants

@Entity(tableName = AppConstants.PRODUCTS_CACHE_TABLE_NAME)
data class ProductCacheEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idProduct: Int,
    val category: String,
    val image: String,
    val price: String,
    val title: String,
    val description: String,
)