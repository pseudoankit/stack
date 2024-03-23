package com.pseudoankit.stack

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.StackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.model.stackScope

@Composable
public fun Stack(
    holder: StackHolder,
    modifier: Modifier = Modifier,
    content: @Composable StackScope.() -> Unit
) {
    val stackScope = stackScope

    Box(modifier = modifier) {
        content(stackScope)
    }
}