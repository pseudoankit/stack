@file:OptIn(ExperimentalMaterial3Api::class)

package com.pseudoankit.stack.ui

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.pseudoankit.stack.ui.model.ChildStackHolder
import com.pseudoankit.stack.ui.model.StackScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun StackScope.ChildStack(
    holder: ChildStackHolder,
    previous: @Composable () -> Unit,
    next: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {

    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )
    HandleState(holder.sheetState, bottomSheetState.bottomSheetState)

    BottomSheetScaffold(
        content = {},
        sheetContent = {
            content()
        }
    )
}

@Composable
private fun HandleState(
    sheetState: SharedFlow<ChildStackHolder.SheetState>,
    bottomSheetState: SheetState
) {
    LaunchedEffect(Unit) {
        sheetState.collectLatest {
            when (it) {
                ChildStackHolder.SheetState.Expanded -> {
                    bottomSheetState.expand()
                }

                ChildStackHolder.SheetState.Hidden -> {
                    bottomSheetState.hide()
                }

                ChildStackHolder.SheetState.PartiallyExpanded -> {
                    bottomSheetState.partialExpand()
                }
            }
        }
    }
}