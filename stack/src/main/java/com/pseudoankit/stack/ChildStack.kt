@file:OptIn(ExperimentalMaterial3Api::class)

package com.pseudoankit.stack

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.ChildStackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.ui.child.BottomSheetChildStackInternal
import com.pseudoankit.stack.ui.child.ChildStackCore
import com.pseudoankit.stack.ui.child.SimpleChildStackInternal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun StackScope.SimpleChildStack(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    previous: @Composable () -> Unit,
    next: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    SimpleChildStackInternal(
        holder = holder,
        modifier = modifier
    ) {
        ChildStackCore(
            holder = holder,
            previous = previous,
            next = next,
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun StackScope.BottomSheetChildStack(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    previous: @Composable () -> Unit,
    next: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    BottomSheetChildStackInternal(
        modifier = modifier,
        holder = holder
    ) {
        ChildStackCore(holder, previous, next, content)
    }
}
