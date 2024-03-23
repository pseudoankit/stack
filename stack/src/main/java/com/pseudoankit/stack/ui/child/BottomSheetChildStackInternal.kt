package com.pseudoankit.stack.ui.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.pseudoankit.stack.model.ChildStackHolder
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheetChildStackInternal(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) {
    val sheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    HandleChildState(holder = holder, bottomSheetState = sheetScaffoldState.bottomSheetState)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .bottomSheetScrim(
                sheetState = sheetScaffoldState.bottomSheetState,
                onDismiss = {}
            )
            .padding(top = holder.topOffset)
    ) {
        BottomSheetScaffold(
            content = {},
            sheetContent = {
                Column(modifier = Modifier.fillMaxSize()) {
                    content()
                }
            },
            modifier = modifier,
            scaffoldState = sheetScaffoldState,
            sheetDragHandle = null,
            sheetPeekHeight = if (holder.sheetContent == ChildStackHolder.SheetContent.Upcoming) 100.dp else 0.dp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HandleChildState(
    holder: ChildStackHolder,
    bottomSheetState: SheetState
) {
    LaunchedEffect(Unit) {
        holder.sheetState.collectLatest {
            when (it) {
                ChildStackHolder.SheetState.Expanded -> {
                    bottomSheetState.expand()
                }

                ChildStackHolder.SheetState.Hidden -> {
                    bottomSheetState.hide()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
internal fun Modifier.bottomSheetScrim(
    sheetState: SheetState,
    onDismiss: () -> Unit,
): Modifier = composed {
    background(
        when (sheetState.currentValue) {
            SheetValue.Expanded -> Color.Black.copy(alpha = .4f)
            SheetValue.Hidden, SheetValue.PartiallyExpanded -> Color.Unspecified
        }
    ).run {
        if (sheetState.currentValue == SheetValue.Expanded) {
            pointerInput(Unit) { onDismiss() }
        } else this
    }
}
