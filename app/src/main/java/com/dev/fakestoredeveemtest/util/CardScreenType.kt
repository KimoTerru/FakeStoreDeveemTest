package com.dev.fakestoredeveemtest.util

import android.view.View

sealed class CardScreenType(val showContent: Int, val showEmptyBox: Int) {
    data object EmptyBox : CardScreenType(View.GONE, View.VISIBLE)
    data object Content : CardScreenType(View.VISIBLE, View.GONE)
}