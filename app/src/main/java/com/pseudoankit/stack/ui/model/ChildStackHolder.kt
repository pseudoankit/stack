package com.pseudoankit.stack.ui.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import androidx.compose.runtime.State as ComposeState


@Stable
class ChildStackHolder internal constructor() {

    private val _sheetState = MutableSharedFlow<SheetState>()
    val sheetState: SharedFlow<SheetState> get() = _sheetState

    private val _sheetContent = mutableStateOf(SheetContent.Upcoming)
    val sheetContent: ComposeState<SheetContent> get() = _sheetContent

    fun moveToBackStack() {

    }

    fun show() {

    }

    fun showNext() {

    }

    fun hide() {

    }

    enum class SheetState {
        Expanded,
        Hidden,
        PartiallyExpanded
    }

    enum class SheetContent {
        BackStack,
        Visible,
        Upcoming
    }
}