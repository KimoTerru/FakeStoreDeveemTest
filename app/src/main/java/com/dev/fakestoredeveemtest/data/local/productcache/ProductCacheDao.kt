package com.dev.fakestoredeveemtest.data.local.productcache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.fakestoredeveemtest.data.local.productcache.models.ProductCacheEntity

@Dao
interface ProductCacheDao {

    @Query("SELECT * FROM PRODUCTS_CACHE_TABLE")
    suspend fun getAllProductsCache(): List<ProductCacheEntity>?

    @Query("SELECT * FROM PRODUCTS_CACHE_TABLE where category= :category ORDER BY category ASC")
    suspend fun getProductsByCategoryCache(category: String): List<ProductCacheEntity>

    @Query("SELECT * FROM PRODUCTS_CACHE_TABLE WHERE id = :id")
    suspend fun getProductByIdInCache(id: Int): ProductCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productList: List<ProductCacheEntity>)

    @Delete
    suspend fun deleteProduct(photo: ProductCacheEntity)

}