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
import com.pseudoankit.stack.ChildStack
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

    Button(onClick = {
        coroutineScope.launch {
            stackHolder.goNext()
        }
    }) {
        Text(text = "Launch")
    }

    Stack(
        holder = stackHolder
    ) {
        ChildStack(
            holder = stackHolder.first,
            "First",
            onNext = next,
            onPrevious = previous
        )
        ChildStack(
            holder = stackHolder.second,
            "Second",
            onNext = next,
            onPrevious = previous
        )
        ChildStack(
            holder = stackHolder.third,
            "Third",
            onNext = next,
            onPrevious = previous
        )
        ChildStack(
            holder = stackHolder.fourth,
            "Fourth",
            onNext = next,
            onPrevious = previous
        )
    }
}

@Composable
private fun StackScope.ChildStack(
    holder: ChildStackHolder, step: String,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    ChildStack(
        holder = holder,
        previous = {
            Text(text = "$step at Backstack")
        },
        next = {
            Text(text = "$step at queue")
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    onPrevious()
                }
            }) {
                Text(text = "Previous")
            }
            Text(text = "Step $step")
            Button(onClick = {
                coroutineScope.launch {
                    onNext()
                }
            }) {
                Text(text = "Next")
            }
        }


    }
}