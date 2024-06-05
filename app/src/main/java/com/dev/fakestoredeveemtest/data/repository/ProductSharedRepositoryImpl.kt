package com.dev.fakestoredeveemtest.data.repository

import android.content.SharedPreferences
import com.dev.fakestoredeveemtest.domain.repository.ProductSharedRepository
import com.dev.fakestoredeveemtest.util.AppConstants
import javax.inject.Inject

class ProductSharedRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
) : ProductSharedRepository {

    override fun saveSingleStateDataDownloaded(dataState: Boolean) {
        preferences.edit().putBoolean(AppConstants.ARGUMENT_PRODUCT, dataState).apply()
    }

    override fun getSingleStateDataDownloaded(): Boolean {
        return preferences.getBoolean(AppConstants.ARGUMENT_PRODUCT, false)
    }

    override fun isDataExists(): Boolean = preferences.contains(AppConstants.ARGUMENT_PRODUCT)

}