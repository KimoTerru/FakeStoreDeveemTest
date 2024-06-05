package com.dev.fakestoredeveemtest.data.repository

import com.dev.fakestoredeveemtest.data.remote.ProductService
import com.dev.fakestoredeveemtest.domain.mapper.toProductModel
import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {

    companion object {
        private const val MAX_LIMIT_LIST = 20
    }

    override suspend fun getAllProducts(): List<ProductModel> {
        val productList = productService.getAllProducts(MAX_LIMIT_LIST)
        return productList.map {
            it.toProductModel()
        }
    }

}