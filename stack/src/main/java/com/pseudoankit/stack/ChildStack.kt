@file:OptIn(ExperimentalMaterial3Api::class)

package com.pseudoankit.stack

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.model.ChildStackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.ui.ChildStackInternal
import com.pseudoankit.stack.ui.HandleChildState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StackScope.ChildStack(
    holder: ChildStackHolder,
    modifier: Modifier = Modifier,
    previous: @Composable () -> Unit,
    next: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {

    ChildStackInternal { sheetScaffoldState ->
        HandleChildState(holder = holder, bottomSheetState = sheetScaffoldState.bottomSheetState)

        content()
    }
}
