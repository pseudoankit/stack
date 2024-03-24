package com.pseudoankit.stack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.pseudoankit.stack.util.StackDefault

@Composable
internal fun HeaderInternal(
    modifier: Modifier,
    showCloseButton: Boolean,
    onCloseClicked: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .padding(StackDefault.headerPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showCloseButton) {
            HeaderItemInternal(icon = Icons.Default.Clear, onClick = onCloseClicked)
        }
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HeaderItemInternal(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String = ""
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .padding(4.dp)
        ) {
            Icon(imageVector = icon, contentDescription = contentDescription, tint = Color.White)
        }
    }
}