package com.pseudoankit.stack

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import com.pseudoankit.stack.model.StackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.ui.HeaderInternal
import com.pseudoankit.stack.ui.HeaderItemInternal
import com.pseudoankit.stack.ui.parent.StackInternal

@Composable
public fun Stack(
    holder: StackHolder,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black.copy(.8f),
    header: @Composable StackScope.() -> Unit = {},
    content: @Composable StackScope.() -> Unit
) {
    StackInternal(
        holder = holder,
        modifier = Modifier
            .graphicsLayer {
                alpha = if (holder.isVisible) 1f else 0f
            }
            .background(backgroundColor)
            .then(modifier),
        content = content,
        header = header
    )
}

@Composable
public fun StackScope.Header(
    modifier: Modifier = Modifier,
    showCloseButton: Boolean = true,
    onCloseClicked: () -> Unit,
    content: @Composable RowScope.() -> Unit = {}
) {
    HeaderInternal(
        modifier = modifier,
        showCloseButton = showCloseButton,
        onCloseClicked = onCloseClicked,
        content = content
    )
}

@Composable
public fun StackScope.HeaderItem(
    icon: ImageVector,
    contentDescription: String = "",
    onClick: () -> Unit,
) {
    HeaderItemInternal(icon = icon, onClick = onClick, contentDescription = contentDescription)
}
