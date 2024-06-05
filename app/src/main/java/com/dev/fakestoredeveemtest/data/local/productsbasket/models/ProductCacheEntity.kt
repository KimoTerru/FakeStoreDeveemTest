package com.dev.fakestoredeveemtest.data.local.productsbasket.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.fakestoredeveemtest.util.AppConstants

@Entity(tableName = AppConstants.PRODUCTS_BASKET_TABLE_NAME)
data class ProductBasketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idProduct: Int,
    val category: String,
    val image: String,
    val price: String,
    val title: String,
    val description: String,
)