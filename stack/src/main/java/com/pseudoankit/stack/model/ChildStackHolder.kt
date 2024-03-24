package com.pseudoankit.stack.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Stable
public class ChildStackHolder internal constructor(
    private val index: Int,
    private val parenHolder: StackHolder,
    sheetContent: SheetContent = SheetContent.Hidden,
    private val backStackViewHeight: Dp,
    private val upcomingViewHeight: Dp,
) {

    private val _sheetState = MutableSharedFlow<SheetState>()
    internal val sheetState: SharedFlow<SheetState> get() = _sheetState

    private val _sheetContent = mutableStateOf(sheetContent)
    internal val sheetContent: SheetContent by _sheetContent

    internal val topOffset = backStackViewHeight * index

    internal suspend fun moveToBackStack() {
        _sheetContent.value = SheetContent.BackStack
    }

    internal suspend fun show() {
        _sheetContent.value = SheetContent.Visible
        _sheetState.emit(SheetState.Expanded)
    }

    internal suspend fun moveToUpcoming() {
        _sheetContent.value = SheetContent.Upcoming
        _sheetState.emit(SheetState.Hidden)
    }

    internal suspend fun hide() {
        _sheetContent.value = SheetContent.Hidden
        _sheetState.emit(SheetState.Hidden)
    }

    internal suspend fun dismissSheet() {
        parenHolder.goPrevious()
    }

    internal val peekHeight get() = if (sheetContent == SheetContent.Upcoming) upcomingViewHeight else 0.dp

    internal enum class SheetState {
        Expanded,
        Hidden,
    }

    internal enum class SheetContent {
        BackStack,
        Visible,
        Upcoming,
        Hidden
    }
}