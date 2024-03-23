package com.pseudoankit.stack

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import com.pseudoankit.stack.model.StackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.model.stackScope
import com.pseudoankit.stack.util.toPx
import kotlin.math.roundToInt

@Composable
public fun Stack(
    holder: StackHolder,
    modifier: Modifier = Modifier,
    content: @Composable StackScope.() -> Unit
) {
    val stackScope = stackScope
    val density: Density = LocalDensity.current

    Layout(
        modifier = modifier,
        content = { content(stackScope) }
    ) { measurables, constraints ->

        fun offset(idx: Int): Int {
            return holder.getChild(idx).topOffset.toPx(density).roundToInt()
        }

        fun childConstraints(idx: Int) = Constraints(
            minWidth = 0,
            minHeight = 0,
            maxWidth = constraints.maxWidth,
            maxHeight = constraints.maxHeight - offset(idx),
        )

        layout(constraints.maxWidth, constraints.maxHeight) {
            for (idx in 0 until holder.childCount) {
                measurables.getOrNull(idx)
                    ?.measure(childConstraints(idx))
                    ?.place(
                        x = 0,
                        y = offset(idx)
                    )
            }
        }
    }
}