package com.dev.fakestoredeveemtest.presentation.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dev.fakestoredeveemtest.R
import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.usecase.home.HomeUseCase
import com.dev.fakestoredeveemtest.util.BuyButtonType
import com.dev.fakestoredeveemtest.util.ProductCategoriesType
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _productModelMutableLiveData = MutableLiveData<List<ProductModel>>()
    val productModelLiveData: LiveData<List<ProductModel>> = _productModelMutableLiveData

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        try {
            if (homeUseCase.getDownloadedStateProducts.invoke()) {
                val productListCache = homeUseCase.getProductsCacheUseCase.invoke()
                _productModelMutableLiveData.postValue(productListCache)
            } else {
                val productList = homeUseCase.getProductsNetworkUseCase.invoke()
                if (productList.isNotEmpty()) {
                    homeUseCase.saveDownloadedStateProducts.invoke(true)
                    _productModelMutableLiveData.postValue(productList)
                    homeUseCase.saveProductsCacheUseCase.invoke(productList)
                } else {
                    homeUseCase.saveDownloadedStateProducts.invoke(false)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val _selectedCategoryMutableLiveData =
        MutableLiveData<ProductCategoriesType>(ProductCategoriesType.All)
    val selectedCategoryLiveData: LiveData<ProductCategoriesType> = _selectedCategoryMutableLiveData

    private val currentCategoryMutableLiveData =
        MutableLiveData<ProductCategoriesType>(ProductCategoriesType.All)

    fun changeCurrentCategoryProducts(categoriesType: ProductCategoriesType) {
        currentCategoryMutableLiveData.value = categoriesType
    }

    fun updateProductsByCategory() = viewModelScope.launch {
        _selectedCategoryMutableLiveData.value = currentCategoryMutableLiveData.value
        when (selectedCategoryLiveData.value) {
            ProductCategoriesType.All -> {
                val productList = homeUseCase.getProductsCacheUseCase.invoke()
                _productModelMutableLiveData.postValue(productList)
            }

            else -> {
                val productListByCategories =
                    homeUseCase.getProductsByCategoriesUseCase.invoke(currentCategoryMutableLiveData.value!!.category)
                _productModelMutableLiveData.value = productListByCategories
            }
        }
    }

    private val _selectedStateBuyButton = MutableLiveData<BuyButtonType>(BuyButtonType.AddToCard)
    val selectedStateBuyButton: LiveData<BuyButtonType> = _selectedStateBuyButton

    fun changeStateBuyButton(buyButtonType: BuyButtonType) {
        _selectedStateBuyButton.value = buyButtonType
    }

    private val _countListenerNavigateToCard = MutableLiveData(0)

    private var counterNavigate = 0

    fun setDoubleClickListener() {
        counterNavigate++
        _countListenerNavigateToCard.value = counterNavigate
    }

    fun navigateToCard(
        findNavController: NavController,
        dialog: BottomSheetDialog
    ) {
        if (_countListenerNavigateToCard.value!! >= 2) {
            findNavController.navigate(R.id.action_fragment_home_to_fragment_card)
            resetCounterNavigate()
            dialog.dismiss()
        }
    }

    fun resetCounterNavigate() {
        counterNavigate = 0
        _countListenerNavigateToCard.value = counterNavigate
    }

    fun saveProductInCart(productModel: ProductModel) = viewModelScope.launch {
        homeUseCase.saveProductToBasketUseCase.invoke(productModel)
    }

}