package com.pseudoankit.stack.model

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.pseudoankit.stack.util.Constant.MAX_CHILD_COUNT
import com.pseudoankit.stack.util.Constant.MIN_CHILD_COUNT
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

// TODO can we remove overhead of passing child holder by client

@Composable
public fun rememberStackHolder(
    @IntRange(MIN_CHILD_COUNT, MAX_CHILD_COUNT) childCount: Int
): StackHolder {
    return StackHolder(childCount = childCount)
}

@Stable
public class StackHolder internal constructor(
    internal val childCount: Int
) {
    init {
        require(childCount in MIN_CHILD_COUNT..MAX_CHILD_COUNT) {
            "Child count of stack must be in range of $MIN_CHILD_COUNT .. $MAX_CHILD_COUNT"
        }
    }

    private var activeChildIndex = -1
        set(value) {
            field = value.coerceAtMost(childCount - 1)
        }

    private val childStackHolders = List(childCount) { ChildStackHolder(it, this) }

    internal val root: ChildStackHolder = ChildStackHolder(
        index = 0,
        parenHolder = this,
        sheetContent = ChildStackHolder.SheetContent.Visible
    )
    public val first: ChildStackHolder = getChild(0)
    public val second: ChildStackHolder = getChild(1)
    public val third: ChildStackHolder = getChild(2)
    public val fourth: ChildStackHolder = getChild(3)

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
            root.show()
            childStackHolders.forEach { it.hide() }
            return
        }

        if (activeChildIndex == 0) {
            root.moveToBackStack()
        }

        coroutineScope {
            launch { childStackHolders.getOrNull(activeChildIndex - 1)?.moveToBackStack() }
            launch { childStackHolders.getOrNull(activeChildIndex)?.show() }
            launch { childStackHolders.getOrNull(activeChildIndex + 1)?.moveToUpcoming() }
            launch { childStackHolders.getOrNull(activeChildIndex + 2)?.hide() }
        }
    }

    private fun getChild(idx: Int): ChildStackHolder {
        require(idx < childCount) {
            "Child ${idx + 1} in stack do not exist, bcz you have specified $childCount child only."
        }

        return childStackHolders[idx]
    }
}