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

    val first get() = childrens[0]
    val second get() = childrens[0]
    val third get() = childrens[0]
    val fourth get() = childrens[0]

    fun next() {
        activeChildIndex++

        childrens.getOrNull(activeChildIndex - 1)?.moveToBackStack()
        childrens.getOrNull(activeChildIndex)?.show()
        childrens.getOrNull(activeChildIndex + 1)?.showNext()
    }

    fun previous() {
        activeChildIndex--

        childrens.getOrNull(activeChildIndex + 1)?.showNext()
        childrens.getOrNull(activeChildIndex)?.show()
    }

    fun close() {

    }
}