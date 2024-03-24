package com.pseudoankit.stack.model

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import com.pseudoankit.stack.util.StackDefault
import com.pseudoankit.stack.util.StackDefault.maxChildCount
import com.pseudoankit.stack.util.StackDefault.minChildCount
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

// TODO can we remove overhead of passing child holder by client

@Composable
public fun rememberStackHolder(
    @IntRange(minChildCount, maxChildCount) childCount: Int,
    backStackViewHeight: Dp = StackDefault.backstackViewHeight,
    upcomingViewHeight: Dp = StackDefault.upcomingViewHeight,
): StackHolder {
    return remember {
        StackHolder(
            childCount = childCount,
            backStackViewHeight = backStackViewHeight,
            upcomingViewHeight = upcomingViewHeight
        )
    }
}

@Stable
public class StackHolder internal constructor(
    internal val childCount: Int,
    private val backStackViewHeight: Dp,
    private val upcomingViewHeight: Dp,
) {
    init {
        require(childCount in minChildCount..maxChildCount) {
            "Child count of stack must be in range of $minChildCount .. $maxChildCount"
        }
    }

    private var activeChildIndex = -1
        set(value) {
            isVisible = value >= 0
            field = value.coerceAtMost(childCount - 1)
        }

    private val childStackHolders = List(childCount, ::buildChild)

    public val first: ChildStackHolder = getChild(0)
    public val second: ChildStackHolder = getChild(1)
    public val third: ChildStackHolder = getChild(2)
    public val fourth: ChildStackHolder = getChild(3)

    internal var isVisible by mutableStateOf(false)
        private set

    public suspend fun show() {
        goNext()
    }

    public suspend fun goNext() {
        activeChildIndex++
        updateChildHolders()
    }

    public suspend fun goPrevious() {
        activeChildIndex--
        updateChildHolders()
    }

    public suspend fun close() {
        activeChildIndex = -1
        updateChildHolders()
    }

    private suspend fun updateChildHolders() {
        if (activeChildIndex == -1) {
            childStackHolders.forEach { childStackHolder ->
                childStackHolder.hide()
            }
            return
        }

        coroutineScope {
            launch { childStackHolders.getOrNull(activeChildIndex - 1)?.moveToBackStack() }
            launch { childStackHolders.getOrNull(activeChildIndex)?.show() }
            launch { childStackHolders.getOrNull(activeChildIndex + 1)?.moveToUpcoming() }
            launch { childStackHolders.getOrNull(activeChildIndex + 2)?.hide() }
        }
    }

    private fun buildChild(
        index: Int,
    ): ChildStackHolder {
        return ChildStackHolder(
            index = index,
            parenHolder = this,
            backStackViewHeight = backStackViewHeight,
            upcomingViewHeight = upcomingViewHeight,
        )
    }

    private fun getChild(idx: Int): ChildStackHolder {
        require(idx < childCount) {
            "Child ${idx + 1} in stack do not exist, bcz you have specified $childCount child only."
        }

        return childStackHolders[idx]
    }
}