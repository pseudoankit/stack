package com.pseudoankit.stack.model

import androidx.compose.runtime.Stable

@Stable
public interface StackScope {
    public val holder: StackHolder
}

internal fun stackScope(
    holder: StackHolder
) = object : StackScope {
    override val holder: StackHolder = holder
}