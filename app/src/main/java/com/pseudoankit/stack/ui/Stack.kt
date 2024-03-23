package com.pseudoankit.stack.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.ui.model.StackHolder
import com.pseudoankit.stack.ui.model.StackScope
import com.pseudoankit.stack.ui.model.stackScope

@Composable
fun Stack(
    holder: StackHolder,
    modifier: Modifier = Modifier,
    content: @Composable StackScope.() -> Unit
) {
    val stackScope = stackScope

    Box(modifier = modifier) {
        content(stackScope)
    }
}