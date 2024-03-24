package com.pseudoankit.stack.ui.parent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.StackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.model.stackScope

@Composable
internal fun StackInternal(
    holder: StackHolder,
    modifier: Modifier = Modifier,
    children: @Composable StackScope.() -> Unit,
    content: @Composable StackScope.() -> Unit,
) {

    val stackScope = remember(holder) {
        stackScope(holder)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        content(stackScope)
        children(stackScope)
    }
}