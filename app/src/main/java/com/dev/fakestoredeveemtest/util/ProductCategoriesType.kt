package com.dev.fakestoredeveemtest.util

import com.dev.fakestoredeveemtest.R

sealed class ProductCategoriesType(val category: String, val id: Int) {
    data object All : ProductCategoriesType("all", R.id.all_button)
    data object Electronics : ProductCategoriesType("electronics", R.id.electronics_button)
    data object Jewelery : ProductCategoriesType("jewelery", R.id.jewelry_button)
    data object Mens_clothing : ProductCategoriesType("men's clothing", R.id.men_s_clothing_button)
    data object Womens_clothing :
        ProductCategoriesType("women's clothing", R.id.women_s_clothing_button)
}