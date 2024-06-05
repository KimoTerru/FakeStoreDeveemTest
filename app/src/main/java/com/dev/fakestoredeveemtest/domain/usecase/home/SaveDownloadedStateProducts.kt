package com.dev.fakestoredeveemtest.domain.usecase.home

import com.dev.fakestoredeveemtest.domain.repository.ProductSharedRepository

class SaveDownloadedStateProducts(
    private val repository: ProductSharedRepository
) {
    fun invoke(dataState: Boolean) {
        return try {
            repository.saveSingleStateDataDownloaded(dataState)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}