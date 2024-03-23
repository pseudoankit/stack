package com.pseudoankit.stack.ui.child

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.pseudoankit.stack.model.ChildStackHolder

@Composable
internal fun ColumnScope.ChildStackCore(
    holder: ChildStackHolder,
    previous: @Composable () -> Unit,
    next: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {

    when (holder.sheetContent) {
        ChildStackHolder.SheetContent.BackStack -> {
            previous()
        }

        ChildStackHolder.SheetContent.Visible -> {}
        ChildStackHolder.SheetContent.Upcoming -> {
            next()
        }

        ChildStackHolder.SheetContent.Hidden -> {}
    }

    if (holder.sheetContent != ChildStackHolder.SheetContent.Hidden) {
        content()
    }
}