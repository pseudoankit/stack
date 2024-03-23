package com.pseudoankit.stack.model

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.pseudoankit.stack.util.Constant.MAX_CHILD_COUNT
import com.pseudoankit.stack.util.Constant.MIN_CHILD_COUNT

// TODO can we remove overhead of passing child holder by client

@Composable
fun rememberStackHolder(
    @IntRange(MIN_CHILD_COUNT, MAX_CHILD_COUNT) childCount: Int
): StackHolder {
    return StackHolder(childCount = childCount)
}

@Stable
class StackHolder internal constructor(
    private val childCount: Int
) {

    init {
        require(childCount in MIN_CHILD_COUNT..MAX_CHILD_COUNT) {
            "Child count of stack must be in range of $MIN_CHILD_COUNT .. $MAX_CHILD_COUNT"
        }
    }

    private var activeChildIndex = -1
    private val childStackHolders = List(childCount) { ChildStackHolder() }

    val first = getChild(0)
    val second = getChild(1)
    val third = getChild(2)
    val fourth = getChild(3)

    suspend fun next() {
        activeChildIndex++

        childStackHolders.getOrNull(activeChildIndex - 1)?.moveToBackStack()
        childStackHolders.getOrNull(activeChildIndex)?.show()
        childStackHolders.getOrNull(activeChildIndex + 1)?.showNext()
    }

    suspend fun previous() {
        activeChildIndex--

        childStackHolders.getOrNull(activeChildIndex + 1)?.showNext()
        childStackHolders.getOrNull(activeChildIndex)?.show()
    }

    suspend fun close() {

    }

    private fun getChild(idx: Int): ChildStackHolder {
        require(idx < childCount) {
            "Child ${idx + 1} in stack do not exist, bcz you have specified $childCount child only."
        }

        return childStackHolders[idx]
    }
}