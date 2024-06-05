package com.dev.fakestoredeveemtest.util

import com.dev.fakestoredeveemtest.R

sealed class BuyButtonType(
    val icon: Int,
    val text: Int,
    val colorContent: Int,
    val backgroundColor: Int,
    val backgroundDrawable: Int
) {
    data object AddToCard : BuyButtonType(
        R.drawable.ic_cart_add,
        R.string.add_to_cart,
        R.color.black,
        R.color.black,
        R.drawable.detail_buy_button_normal
    )

    data object GoToCard : BuyButtonType(
        R.drawable.ic_cart_go,
        R.string.go_to_cart,
        R.color.white,
        R.color.black,
        R.drawable.detail_buy_button_selected
    )

    data object AddSomething : BuyButtonType(
        R.drawable.ic_basket_empty,
        R.string.add_something,
        R.color.black,
        R.color.black,
        R.drawable.detail_buy_button_normal
    )

    data object Buy : BuyButtonType(
        R.drawable.ic_basket_fill,
        R.string.buy,
        R.color.white,
        R.color.black,
        R.drawable.detail_buy_button_selected
    )

}