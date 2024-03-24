package com.pseudoankit.stack.ui.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pseudoankit.stack.model.ChildStackHolder
import com.pseudoankit.stack.util.clickable
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheetChildStackInternal(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit),
) {
    val sheetShape = BottomSheetDefaults.ExpandedShape
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
            .run {
                if (sheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                    clickable(showRipple = false, onClick = onDismiss)
                } else this
            }
            .padding(top = holder.topOffset)
            .clip(sheetShape)
            .bottomSheetScrim(
                sheetState = sheetScaffoldState.bottomSheetState,
                scrimColor = holder.scrimColor
            )
            .padding(top = holder.topOffsetInScrim)
    ) {
        BottomSheetScaffold(
            content = {},
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(showRipple = false) {}
                ) {
                    content()
                }
            },
            modifier = modifier,
            scaffoldState = sheetScaffoldState,
            sheetDragHandle = null,
            sheetPeekHeight = remember(holder.sheetContent) { holder.peekHeight },
            sheetSwipeEnabled = false,
            sheetShadowElevation = 5.dp,
            sheetTonalElevation = 5.dp,
            sheetShape = sheetShape
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
private fun Modifier.bottomSheetScrim(
    sheetState: SheetState,
    scrimColor: Color,
): Modifier {
    return this
        .background(
            when (sheetState.currentValue) {
                SheetValue.Expanded -> scrimColor
                SheetValue.Hidden, SheetValue.PartiallyExpanded -> Color.Unspecified
            }
        )
}
