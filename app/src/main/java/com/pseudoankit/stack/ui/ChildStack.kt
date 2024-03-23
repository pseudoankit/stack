@file:OptIn(ExperimentalMaterial3Api::class)

package com.pseudoankit.stack.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.ui.model.ChildStackHolder
import com.pseudoankit.stack.ui.model.StackScope
import kotlinx.coroutines.flow.collectLatest

@Composable
fun StackScope.ChildStack(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    previous: @Composable () -> Unit,
    next: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {

    val sheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )
    HandleState(holder = holder, bottomSheetState = sheetScaffoldState.bottomSheetState)

    BottomSheetScaffold(
        content = {},
        sheetContent = {
            Box(modifier = Modifier.fillMaxSize()) {
                content()
            }
        },
        modifier = modifier,
        scaffoldState = sheetScaffoldState
    )
}

@Composable
private fun HandleState(
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

                ChildStackHolder.SheetState.PartiallyExpanded -> {
                    bottomSheetState.partialExpand()
                }
            }
        }
    }
}