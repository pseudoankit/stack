package com.pseudoankit.stack.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import androidx.compose.runtime.State as ComposeState

@Stable
class ChildStackHolder internal constructor() {

    private val _sheetState = MutableSharedFlow<SheetState>()
    internal val sheetState: SharedFlow<SheetState> get() = _sheetState

    private val _sheetContent = mutableStateOf(SheetContent.Upcoming)
    internal val sheetContent: ComposeState<SheetContent> get() = _sheetContent


    internal suspend fun moveToBackStack() {
    }

    internal suspend fun show() {
        _sheetState.emit(SheetState.Expanded)
    }

    internal suspend fun showNext() {
        _sheetState.emit(SheetState.PartiallyExpanded)
    }

    internal suspend fun hide() {
        _sheetState.emit(SheetState.Hidden)
    }

    internal enum class SheetState {
        Expanded,
        Hidden,
        PartiallyExpanded
    }

    internal enum class SheetContent {
        BackStack,
        Visible,
        Upcoming
    }
}