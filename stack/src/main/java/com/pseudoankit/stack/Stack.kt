package com.pseudoankit.stack

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.StackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.ui.parent.StackInternal

@Composable
public fun Stack(
    holder: StackHolder,
    modifier: Modifier = Modifier,
    content: @Composable StackScope.() -> Unit
) {
    StackInternal(
        holder = holder,
        modifier = modifier,
        content = content,
    )
}