package com.pseudoankit.stack.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.ChildStackHolder
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChildStackInternal(
    modifier: Modifier = Modifier,
    content: @Composable (BottomSheetScaffoldState) -> Unit,
) {
    val sheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    BottomSheetScaffold(
        content = {},
        sheetContent = {
            Box(modifier = Modifier.fillMaxSize()) {
                content(sheetScaffoldState)
            }
        },
        modifier = modifier,
        scaffoldState = sheetScaffoldState
    )
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

                ChildStackHolder.SheetState.PartiallyExpanded -> {
                    bottomSheetState.partialExpand()
                }
            }
        }
    }
}