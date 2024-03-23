package com.pseudoankit.stack.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Composable
fun rememberStackHolder(): StackHolder {
    return StackHolder()
}

@Stable
class StackHolder internal constructor() {

    private var activeChildIndex = -1
    private val childrens = listOf(
        ChildStackHolder(),
        ChildStackHolder(),
        ChildStackHolder(),
        ChildStackHolder(),
    )

    val first = childrens[0]
    val second = childrens[1]
    val third = childrens[2]
    val fourth = childrens[3]

    suspend fun next() {
        activeChildIndex++

        childrens.getOrNull(activeChildIndex - 1)?.moveToBackStack()
        childrens.getOrNull(activeChildIndex)?.show()
        childrens.getOrNull(activeChildIndex + 1)?.showNext()
    }

    suspend fun previous() {
        activeChildIndex--

        childrens.getOrNull(activeChildIndex + 1)?.showNext()
        childrens.getOrNull(activeChildIndex)?.show()
    }

    suspend fun close() {

    }
}