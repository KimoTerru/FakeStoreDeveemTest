package com.dev.fakestoredeveemtest.domain.repository

interface ProductSharedRepository {
    fun saveSingleStateDataDownloaded(dataState: Boolean)
    fun getSingleStateDataDownloaded(): Boolean

    fun isDataExists(): Boolean

}