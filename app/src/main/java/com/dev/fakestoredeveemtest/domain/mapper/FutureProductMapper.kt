package com.dev.fakestoredeveemtest.domain.mapper

import com.dev.fakestoredeveemtest.data.local.productcache.models.ProductCacheEntity
import com.dev.fakestoredeveemtest.data.local.productsbasket.models.ProductBasketEntity
import com.dev.fakestoredeveemtest.data.remote.models.ProductResponse
import com.dev.fakestoredeveemtest.domain.models.ProductModel

fun ProductResponse.toProductModel(): ProductModel {
    return ProductModel(
        id = id,
        idProduct = id,
        category = category.toString(),
        image = image.toString(),
        price = price.toString(),
        title = title.toString(),
        description = description.toString()
    )
}

fun ProductCacheEntity.toProductModel(): ProductModel {
    return ProductModel(
        id = id,
        idProduct = idProduct,
        category = category,
        image = image,
        price = price,
        title = title,
        description = description
    )
}

fun ProductBasketEntity.toProductModel(): ProductModel {
    return ProductModel(
        id = id,
        idProduct = idProduct,
        category = category,
        image = image,
        price = price,
        title = title,
        description = description
    )
}

fun ProductModel.toProductEntity(): ProductCacheEntity {
    return ProductCacheEntity(
        id = id,
        idProduct = idProduct,
        category = category,
        image = image,
        price = price,
        title = title,
        description = description
    )
}

fun ProductModel.toProductBasketEntity(): ProductBasketEntity {
    return ProductBasketEntity(
        id = id,
        idProduct = idProduct,
        category = category,
        image = image,
        price = price,
        title = title,
        description = description
    )
}