package com.dev.fakestoredeveemtest.presentation.screen.card

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.domain.usecase.card.CardUseCase
import com.dev.fakestoredeveemtest.util.BuyButtonType
import com.dev.fakestoredeveemtest.util.ScreenType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardUseCase: CardUseCase,
) : ViewModel() {

    private val _productModelMutableLiveData = MutableLiveData<List<ProductModel>>()
    val productModelLiveData: LiveData<List<ProductModel>> = _productModelMutableLiveData

    private val _stateScreen = MutableLiveData<ScreenType>(ScreenType.EmptyBox)
    val stateScreen: LiveData<ScreenType> = _stateScreen

    fun getAllProductsBasket() = viewModelScope.launch {
        val basketList = cardUseCase.getAllProductBasketUseCase.invoke()
        if (basketList.isNotEmpty()) {
            _stateScreen.value = ScreenType.Content
            _productModelMutableLiveData.value = basketList
            _stateStateBuy.value = BuyButtonType.Buy
        } else {
            _stateScreen.value = ScreenType.EmptyBox
            _stateStateBuy.value = BuyButtonType.AddToCard
        }
    }

    fun deleteProduct(cardModel: ProductModel) = viewModelScope.launch {
        getAllProductsBasket()
        cardUseCase.deleteProductBasketUseCase.invoke(cardModel)
    }

    private val _stateStateBuy = MutableLiveData<BuyButtonType>(BuyButtonType.AddSomething)
    val stateBuyButton: LiveData<BuyButtonType> = _stateStateBuy

    fun navigateToProducts(
        findNavController: NavController,
        context: Context
    ) = viewModelScope.launch {
        val nameBasketProducts = cardUseCase.getAllProductBasketUseCase.invoke().map {
            it.title
        }.toString()

        if (_stateStateBuy.value == BuyButtonType.Buy) {
            Toast.makeText(context, nameBasketProducts, Toast.LENGTH_SHORT).show()
            cardUseCase.cleanBasketUseCase.invoke()
            getAllProductsBasket()
        } else {
            findNavController.popBackStack()
        }
    }
}