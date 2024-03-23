package com.pseudoankit.stack.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Stable
class ChildStackHolder internal constructor() {

    private val _sheetState = MutableSharedFlow<SheetState>()
    internal val sheetState: SharedFlow<SheetState> get() = _sheetState

    private val _sheetContent = mutableStateOf(SheetContent.Hidden)
    internal val sheetContent: SheetContent by _sheetContent


    internal suspend fun moveToBackStack() {
        _sheetContent.value = SheetContent.BackStack
    }

    internal suspend fun show() {
        _sheetContent.value = SheetContent.Visible
        _sheetState.emit(SheetState.Expanded)
    }

    internal suspend fun showNext() {
        _sheetContent.value = SheetContent.Upcoming
        _sheetState.emit(SheetState.Hidden)
    }

    internal suspend fun hide() {
        _sheetState.emit(SheetState.Hidden)
    }

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