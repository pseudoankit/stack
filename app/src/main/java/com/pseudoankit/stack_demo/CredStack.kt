package com.pseudoankit.stack_demo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pseudoankit.stack.BackStackView
import com.pseudoankit.stack.BottomSheetChildStack
import com.pseudoankit.stack.ChildSheetContent
import com.pseudoankit.stack.Header
import com.pseudoankit.stack.HeaderItem
import com.pseudoankit.stack.Stack
import com.pseudoankit.stack.UpcomingView
import com.pseudoankit.stack.model.StackScope
import com.pseudoankit.stack.model.rememberStackHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random

val backgroundColor = Color.Black.copy(alpha = .8f)

@Composable
fun BoxScope.CredStack() {
    val stackHolder = rememberStackHolder(childCount = 4)

    val coroutineScope = rememberCoroutineScope()
    val next: () -> Unit = {
        coroutineScope.launch { stackHolder.goNext() }
    }

    val previous: () -> Unit = {
        coroutineScope.launch { stackHolder.goPrevious() }
    }

    LaunchedEffect(Unit) {
        delay(200)
        stackHolder.show()
    }

    Button(
        onClick = {
            coroutineScope.launch { stackHolder.show() }
        },
        modifier = Modifier.align(Alignment.Center)
    ) {
        Text(text = "Show")
    }

    Stack(
        holder = stackHolder,
        header = { Header() },
        onBackPress = previous
    ) {
        Child1()
        Child2(next = next)
        Child3(next = next)
        Child4(next = {})
    }
}

@Composable
private fun StackScope.Child1() {
    BottomSheetChildStack(
        holder = holder.first,
        backStackView = {
            BackStackView(
                modifier = Modifier.background(backgroundColor)
            ) {
                Column {
                    Text(
                        text = "credit amount",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "Rs. 1,50,000",
                        fontSize = 16.sp,
                        color = Color.Gray.copy(alpha = .8f)
                    )
                }
            }
        }
    ) {
        ChildSheetContent(
            modifier = Modifier.background(backgroundColor)
        ) {
            Text(
                text = "ankit, how much do you need?",
                fontSize = 17.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "move the dial and set any amount you need upto Rs.5,00,000",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .padding(top = 48.dp, start = 32.dp, end = 32.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                ) {
                    drawCircle(
                        color = Color.Yellow.copy(alpha = .3f),
                        style = Stroke(
                            width = 12f,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "stash is instant money will be credited within seconds.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun StackScope.Child2(
    next: () -> Unit
) {
    BottomSheetChildStack(
        holder = holder.second,
        backStackView = {
            BackStackView(
                modifier = Modifier.background(backgroundColor)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "EMI",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Rs 2,000 / mo",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column {
                        Text(
                            text = "duration",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "12 months",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        },
        upcomingView = {
            UpcomingViewCred(
                onClick = next,
                text = "Proceed to EMI selection"
            )
        }
    ) {
        ChildSheetContent(
            modifier = Modifier.background(backgroundColor)
        ) {
            Text(
                text = "how do you wish to repay?",
                fontSize = 18.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "choose one of our recommendation plan or make your own",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(5) {
                    val color = randomColor
                    Column(
                        modifier = Modifier
                            .width(100.dp)
                            .height(125.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(color),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Canvas(modifier = Modifier.size(48.dp)) {
                            drawCircle(
                                color = Color.Gray,
                                style = Stroke(width = 8f)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Rs.${Random().nextInt(5)}/mo",
                            fontSize = 10.sp,
                            color = Color.White
                        )
                        Text(
                            text = "for 12 months",
                            fontSize = 8.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Create your own plan",
                modifier = Modifier
                    .border(2.dp, Color.White, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .padding(12.dp),
                color = Color.White
            )
        }
    }
}

@Composable
private fun StackScope.Child3(
    next: () -> Unit
) {
    BottomSheetChildStack(
        holder = holder.third,
        backStackView = {

        },
        upcomingView = {
            UpcomingViewCred(
                onClick = next,
                text = "Select your bank account"
            )
        }
    ) {
        ChildSheetContent(
            modifier = Modifier.background(backgroundColor)
        ) {
            Text(
                text = "where should we send the money?",
                fontSize = 18.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "amount will be credited to this bank account, EMI will also be debited from this bank account",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Paytm Payments Bank",
                        color = Color.White
                    )
                    Text(
                        text = "9100000000",
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Change account",
                modifier = Modifier
                    .border(2.dp, Color.White, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .padding(12.dp),
                color = Color.White
            )
        }
    }
}

@Composable
private fun StackScope.Child4(
    next: () -> Unit
) {
    BottomSheetChildStack(
        holder = holder.fourth,
        backStackView = {

        },
        upcomingView = {
            UpcomingViewCred(
                onClick = next,
                text = "Tap for 1-click KYC"
            )
        }
    ) {

    }
}

@Composable
private fun StackScope.UpcomingViewCred(
    text: String,
    onClick: () -> Unit,
) {
    UpcomingView(
        onClick = onClick,
        modifier = Modifier.background(Color.Blue)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
private fun StackScope.Header() {
    val coroutineScope = rememberCoroutineScope()
    Header(
        onCloseClicked = {
            coroutineScope.launch { holder.close() }
        }
    ) {
        Spacer(modifier = Modifier.weight(1f))
        HeaderItem(
            icon = Icons.Filled.Info,
            onClick = {}
        )
    }
}