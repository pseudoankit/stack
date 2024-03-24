package com.pseudoankit.stack.ui.child

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.pseudoankit.stack.util.StackDefault
import com.pseudoankit.stack.util.clickable

@Composable
internal fun CoreChildView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .padding(
                top = StackDefault.bsContentTopPadding,
                start = StackDefault.bsContentHorizontalPadding,
                end = StackDefault.bsContentHorizontalPadding,
            )
    ) {
        content()
    }
}

@Composable
internal fun BackStackViewInternal(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    CoreChildView(
        modifier = Modifier
            .height(StackDefault.backstackViewHeight)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                content()
                Spacer(modifier = Modifier.width(12.dp))
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "collapse",
                tint = Color.White,
                modifier = Modifier.zIndex(1f)
            )
        }
    }
}

@Composable
internal fun UpcomingViewInternal(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    CoreChildView(
        modifier = Modifier
            .height(StackDefault.upcomingViewHeight)
            .clickable(showRipple = false, onClick = onClick)
            .then(modifier),
        content = content
    )
}

@Composable
internal fun ChildSheetContentInternal(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    CoreChildView(
        modifier = modifier,
        content = content
    )
}
