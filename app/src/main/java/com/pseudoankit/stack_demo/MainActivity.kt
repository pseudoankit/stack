package com.pseudoankit.stack_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.ChildStack
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                StackDemo()
            }
        }
    }
}

@Composable
fun StackDemo() {
    val coroutineScope = rememberCoroutineScope()
    val stackHolder = com.pseudoankit.stack.model.rememberStackHolder()

    Button(onClick = {
        coroutineScope.launch {
            stackHolder.next()
        }
    }) {
        Text(text = "Launch")
    }

    com.pseudoankit.stack.Stack(
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
