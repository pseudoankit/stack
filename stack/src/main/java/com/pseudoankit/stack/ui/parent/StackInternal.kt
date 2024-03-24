package com.pseudoankit.stack.ui.parent

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
    onBackPress: () -> Unit,
    content: @Composable (StackScope.() -> Unit),
    header: @Composable (StackScope.() -> Unit),
) {
    val stackScope = remember(holder) {
        stackScope(holder)
    }

    if (holder.isVisible) {
        BackHandler(onBack = onBackPress)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        if (holder.isVisible) {
            header(stackScope)
        }
        Box {
            content(stackScope)
        }
    }
}