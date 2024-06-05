package com.dev.fakestoredeveemtest.domain.usecase.home

import com.dev.fakestoredeveemtest.domain.repository.ProductSharedRepository

class GetDownloadedStateProducts(
    private val repository: ProductSharedRepository
) {
    fun invoke(): Boolean {
        return try {
            repository.getSingleStateDataDownloaded() and repository.isDataExists()
        } catch (e: Exception) {
            throw e
        }
    }
}