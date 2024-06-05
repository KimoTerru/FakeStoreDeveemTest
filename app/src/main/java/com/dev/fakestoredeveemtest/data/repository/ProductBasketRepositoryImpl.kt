package com.dev.fakestoredeveemtest.data.repository

import com.dev.fakestoredeveemtest.data.local.productsbasket.ProductBasketDao
import com.dev.fakestoredeveemtest.domain.mapper.toProductBasketEntity
import com.dev.fakestoredeveemtest.domain.mapper.toProductModel
import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductBasketRepository
import javax.inject.Inject

class ProductBasketRepositoryImpl @Inject constructor(
    private val productBasketDao: ProductBasketDao
) : ProductBasketRepository {
    override suspend fun getAllBasketProduct(): List<ProductModel> {
        return productBasketDao.getAllProductsCache().map {
            it.toProductModel()
        }
    }

    override suspend fun saveBasketProduct(product: ProductModel) {
        return productBasketDao.insertProducts(product.toProductBasketEntity())
    }

    override suspend fun deleteBasketProduct(product: ProductModel) {
        return productBasketDao.deleteProduct(product.toProductBasketEntity())
    }

    override suspend fun cleanBasket() {
        return productBasketDao.deleteAllProducts()
    }
}