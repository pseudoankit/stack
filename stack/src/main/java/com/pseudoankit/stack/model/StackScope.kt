package com.pseudoankit.stack.model

import androidx.compose.runtime.Stable

@Stable
interface StackScope

val stackScope = object : StackScope {}