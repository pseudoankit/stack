package com.pseudoankit.stack.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pseudoankit.stack.BackStackView
import com.pseudoankit.stack.BottomSheetChildStack
import com.pseudoankit.stack.ChildSheetContent
import com.pseudoankit.stack.Header
import com.pseudoankit.stack.Stack
import com.pseudoankit.stack.UpcomingView
import com.pseudoankit.stack.model.ChildStackHolder
import com.pseudoankit.stack.model.StackHolder
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Root Screen")
        Button(onClick = {
            coroutineScope.launch { stackHolder.show() }
        }) {
            Text(text = "Show Stack")
        }
    }

    PlotStack(
        stackHolder = stackHolder,
        next = next,
        previous = previous,
        onCloseClicked = {
            coroutineScope.launch { stackHolder.close() }
        }
    )
}

@Composable
private fun PlotStack(
    stackHolder: StackHolder,
    next: () -> Unit,
    previous: () -> Unit,
    onCloseClicked: () -> Unit
) {
    Stack(
        holder = stackHolder,
        header = {
            Header(
                onCloseClicked = onCloseClicked
            )
        }
    ) {
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
            BackStackView {
                Text(text = "$step at Backstack", color = Color.White, fontSize = 22.sp)
            }
        },
        upcomingView = {
            UpcomingView(
                modifier = Modifier.background(Color.Blue),
                onClick = onNext
            ) {
                Text(text = "$step in queue", color = Color.White)
            }
        }
    ) {
        ChildSheetContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Step $step")
                Button(onClick = onPrevious) {
                    Text(text = "Go Previous")
                }
                Button(onClick = onNext) {
                    Text(text = "Go Next")
                }
            }
        }
    }
}