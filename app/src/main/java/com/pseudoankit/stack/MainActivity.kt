package com.pseudoankit.stack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.stack.ui.Stack
import com.pseudoankit.stack.ui.ChildStack
import com.pseudoankit.stack.ui.model.rememberStackHolder

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
    val stackHolder = rememberStackHolder()

    Stack(
        holder = stackHolder
    ) {
        ChildStack(
            holder = stackHolder.first,
            previous = { },
            next = { }
        ) {

        }

        ChildStack(
            holder = stackHolder.second,
            previous = { },
            next = { }
        ) {

        }
    }
}
