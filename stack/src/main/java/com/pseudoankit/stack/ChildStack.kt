package com.pseudoankit.stack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.ChildStackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.ui.child.BottomSheetChildStackInternal
import com.pseudoankit.stack.ui.child.ChildStackCore
import kotlinx.coroutines.launch

@Composable
public fun StackScope.Root(
    modifier: Modifier = Modifier,
    backStackView: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        ChildStackCore(
            holder = holder.root,
            backStackView = backStackView,
            content = content,
            upcomingView = {}
        )
    }
}

@Composable
public fun StackScope.BottomSheetChildStack(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    backStackView: @Composable () -> Unit,
    upcomingView: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    BottomSheetChildStackInternal(
        modifier = modifier,
        holder = holder,
        onDismiss = {
            coroutineScope.launch { holder.dismissSheet() }
        }
    ) {
        ChildStackCore(
            holder = holder,
            backStackView = backStackView,
            upcomingView = upcomingView,
            content = content
        )
    }
}
