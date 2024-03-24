package com.pseudoankit.stack.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pseudoankit.stack.util.StackDefault
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Stable
public class ChildStackHolder internal constructor(
    internal val index: Int,
    private val parenHolder: StackHolder,
    private val backStackViewHeight: Dp,
    private val upcomingViewHeight: Dp,
) {

    private val _sheetState = MutableSharedFlow<SheetState>()
    internal val sheetState: SharedFlow<SheetState> get() = _sheetState

    private val _sheetContent = mutableStateOf(SheetContent.Hidden)
    internal val sheetContent: SheetContent by _sheetContent

    internal val topOffset = backStackViewHeight * (index - 1).coerceAtLeast(0)
    internal val topOffsetInScrim = if (index == 0) 0.dp else backStackViewHeight
    internal val scrimColor = if (index == 0) Color.Unspecified else StackDefault.bsScrimColor


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