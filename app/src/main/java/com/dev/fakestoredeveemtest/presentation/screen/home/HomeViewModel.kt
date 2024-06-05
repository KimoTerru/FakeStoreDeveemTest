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
import com.dev.fakestoredeveemtest.util.ScreenType
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

    private val _stateScreen = MutableLiveData<ScreenType>(ScreenType.EmptyBox)
    val stateScreen: LiveData<ScreenType> = _stateScreen

    fun getProducts() = viewModelScope.launch {
        val productListCache = homeUseCase.getProductsCacheUseCase.invoke()
        if (productListCache.isNotEmpty()) {
            _stateScreen.value = ScreenType.Content
            _productModelMutableLiveData.postValue(productListCache)
        } else {
            _stateScreen.value = ScreenType.EmptyBox
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
                    homeUseCase.getProductsByCategoriesCacheUseCase.invoke(
                        currentCategoryMutableLiveData.value!!.category
                    )
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