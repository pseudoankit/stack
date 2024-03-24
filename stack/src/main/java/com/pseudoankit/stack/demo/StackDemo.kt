package com.pseudoankit.stack.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pseudoankit.stack.BottomSheetChildStack
import com.pseudoankit.stack.Root
import com.pseudoankit.stack.Stack
import com.pseudoankit.stack.model.ChildStackHolder
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.model.rememberStackHolder
import kotlinx.coroutines.launch

@Preview
@Composable
public fun StackDemo() {
    val coroutineScope = rememberCoroutineScope()
    val stackHolder = rememberStackHolder(childCount = 4)
    val next: () -> Unit = {
        coroutineScope.launch { stackHolder.goNext() }
    }

    val previous: () -> Unit = {
        coroutineScope.launch { stackHolder.goPrevious() }
    }

    Stack(
        holder = stackHolder,
        children = {
            BottomSheet(
                holder = stackHolder.first,
                "First",
                onNext = next,
                onPrevious = previous
            )
            BottomSheet(
                holder = stackHolder.second,
                "Second",
                onNext = next,
                onPrevious = previous
            )
            BottomSheet(
                holder = stackHolder.third,
                "Third",
                onNext = next,
                onPrevious = previous
            )
            BottomSheet(
                holder = stackHolder.fourth,
                "Fourth",
                onNext = next,
                onPrevious = previous
            )
        }
    ) {
        Root(
            backStackView = {
                Text(text = "First at Backstack")
            },
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = previous) {
                    Text(text = "Previous")
                }
                Text(text = "Root")
                Button(onClick = next) {
                    Text(text = "Next")
                }
            }
        }
    }
}

@Composable
private fun StackScope.BottomSheet(
    holder: ChildStackHolder, step: String,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    BottomSheetChildStack(
        holder = holder,
        backStackView = {
            Text(text = "$step at Backstack")
        },
        upcomingView = {
            Text(text = "$step at queue")
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onPrevious) {
                Text(text = "Previous")
            }
            Text(text = "Step $step")
            Button(onClick = onNext) {
                Text(text = "Next")
            }
        }
    }
}