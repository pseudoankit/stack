package com.pseudoankit.stack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.ChildStackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.ui.child.BackStackViewInternal
import com.pseudoankit.stack.ui.child.BottomSheetChildStackInternal
import com.pseudoankit.stack.ui.child.ChildSheetContentInternal
import com.pseudoankit.stack.ui.child.ChildStackCore
import com.pseudoankit.stack.ui.child.UpcomingViewInternal
import kotlinx.coroutines.launch

@Composable
public fun StackScope.BottomSheetChildStack(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    backStackView: @Composable () -> Unit = {},
    upcomingView: @Composable () -> Unit = {},
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

@Composable
public fun StackScope.BackStackView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    BackStackViewInternal(
        modifier = modifier,
        content = content
    )
}

@Composable
public fun StackScope.UpcomingView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    UpcomingViewInternal(
        modifier = modifier,
        content = content,
        onClick = onClick
    )
}

@Composable
public fun StackScope.ChildSheetContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ChildSheetContentInternal(
        modifier = modifier,
        content = content
    )
}
