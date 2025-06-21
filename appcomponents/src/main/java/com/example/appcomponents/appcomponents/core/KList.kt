package com.example.appcomponents.appcomponents.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

object KList {
    fun <T> padding(dp: Dp) = KListScope<T>().padding(dp)
    fun <T> header(title: String) = KListScope<T>().header(title)
    fun <T> dividers() = KListScope<T>().dividers()
    fun <T> animate() = KListScope<T>().animate()
    fun <T> clickable(onClick: (T) -> Unit) = KListScope<T>().clickable(onClick)

    @Composable
    fun <T> KListScope<T>.items(list: List<T>, itemContent: @Composable (T) -> Unit) {
        section(null, list).render(itemContent)
    }
}