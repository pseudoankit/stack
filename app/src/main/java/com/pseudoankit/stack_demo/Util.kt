package com.pseudoankit.stack_demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import java.util.Random

val randomColor: Color
    @Composable get() {
        return remember {
            val random = Random()
            Color(
                alpha = 255,
                red = random.nextInt(256),
                green = random.nextInt(256),
                blue = random.nextInt(256),
            )
        }
    }

