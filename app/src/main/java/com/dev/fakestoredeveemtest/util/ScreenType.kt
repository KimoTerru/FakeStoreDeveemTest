package com.dev.fakestoredeveemtest.util

import android.view.View

sealed class ScreenType(val showContent: Int, val showEmptyBox: Int) {
    data object EmptyBox : ScreenType(View.GONE, View.VISIBLE)
    data object Content : ScreenType(View.VISIBLE, View.GONE)
}