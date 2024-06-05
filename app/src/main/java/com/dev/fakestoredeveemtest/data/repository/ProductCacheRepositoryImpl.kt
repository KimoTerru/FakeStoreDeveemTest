package com.dev.fakestoredeveemtest.data.repository

import com.dev.fakestoredeveemtest.data.local.productcache.ProductCacheDao
import com.dev.fakestoredeveemtest.domain.mapper.toProductEntity
import com.dev.fakestoredeveemtest.domain.mapper.toProductModel
import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductCacheRepository
import javax.inject.Inject

class ProductCacheRepositoryImpl @Inject constructor(
    private val productCacheDao: ProductCacheDao
) : ProductCacheRepository {

    override suspend fun getAllProducts(): List<ProductModel> {
        return productCacheDao.getAllProductsCache()!!.map {
            it.toProductModel()
        }
    }

    override suspend fun getProductById(id: Int): ProductModel {
        return productCacheDao.getProductByIdInCache(id).toProductModel()
    }

    override suspend fun getProductsByCategories(category: String): List<ProductModel> {
        return productCacheDao.getProductsByCategoryCache(category).map {
            it.toProductModel()
        }
    }

    override suspend fun saveProducts(productList: List<ProductModel>) {
        return productCacheDao.insertProducts(
            productList.map {
                it.toProductEntity()
            }
        )
    }

    override suspend fun clearAllProducts() {
        TODO("Not yet implemented")
    }

}