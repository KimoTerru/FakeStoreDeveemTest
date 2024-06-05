package com.dev.fakestoredeveemtest.data.local.productsbasket

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dev.fakestoredeveemtest.data.local.productsbasket.models.ProductBasketEntity

@Dao
interface ProductBasketDao {

    @Query("SELECT * FROM PRODUCTS_BASKET_TABLE")
    suspend fun getAllProductsCache(): List<ProductBasketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productList: ProductBasketEntity)

    @Delete
    suspend fun deleteProduct(product: ProductBasketEntity)

    @Query("DELETE FROM PRODUCTS_BASKET_TABLE")
    suspend fun deleteAllProducts()

}