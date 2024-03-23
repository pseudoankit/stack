package com.pseudoankit.stack.ui.child

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.ChildStackHolder

@Composable
internal fun SimpleChildStackInternal(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
    ) {
        content()
    }
}