package com.dev.fakestoredeveemtest.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dev.fakestoredeveemtest.R
import com.dev.fakestoredeveemtest.domain.usecase.splash.SplashUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase
) : ViewModel() {

    fun saveCacheProducts() = viewModelScope.launch {
        val data = splashUseCase.getProductsNetworkUseCase.invoke()
        if (data.isNotEmpty()) {
            splashUseCase.saveProductsCacheUseCase.invoke(data)
        }
    }

    fun navigateToHomeScreen(findNavController: NavController) = viewModelScope.launch {
        delay(1000)
        findNavController.navigate(R.id.action_fragment_splash_to_fragment_home)
    }

}