package com.pseudoankit.stack.model

import androidx.compose.runtime.Stable

@Stable
public interface StackScope

internal val stackScope = object : StackScope {}