package com.pseudoankit.stack.ui.child

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.util.Constant

@Composable
internal fun CoreChildView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .padding(Constant.BS_CONTENT_TOP_PADDING)
    ) {
        content()
    }
}