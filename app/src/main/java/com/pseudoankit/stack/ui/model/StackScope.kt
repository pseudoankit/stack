package com.pseudoankit.stack.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Stable
interface StackScope

val stackScope = object : StackScope {}