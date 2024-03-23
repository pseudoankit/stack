package com.pseudoankit.stack.demo

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.pseudoankit.stack.ChildStack
import com.pseudoankit.stack.Stack
import com.pseudoankit.stack.model.rememberStackHolder
import kotlinx.coroutines.launch

@Preview
@Composable
fun StackDemo() {
    val coroutineScope = rememberCoroutineScope()
    val stackHolder = rememberStackHolder(childCount = 4)

    Button(onClick = {
        coroutineScope.launch {
            stackHolder.next()
        }
    }) {
        Text(text = "Launch")
    }

    Stack(
        holder = stackHolder
    ) {
        ChildStack(
            holder = stackHolder.first,
            previous = { },
            next = { }
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    stackHolder.next()
                }
            }) {
                Text(text = "First")
            }
        }

        ChildStack(
            holder = stackHolder.second,
            previous = { },
            next = { }
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    stackHolder.next()
                }
            }) {
                Text(text = "Second")
            }
        }
    }
}