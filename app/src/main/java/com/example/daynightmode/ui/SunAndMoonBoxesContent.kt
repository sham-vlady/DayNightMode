package com.example.daynightmode.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.daynightmode.backgroundColor

@Composable
fun SunAndMoonBoxesContent(
    isDayMode: Boolean
) {
    val firstCircleSize = remember { 100.dp }
    val secondCircleSize = remember { firstCircleSize * 0.7f }

    Box(
        modifier = Modifier
            .size(firstCircleSize)
            .rotate(-40f)
    ) {
        FirstCircle(isDayMode = isDayMode)
        SecondCircle(
            isDayMode = isDayMode,
            secondCircleSize = secondCircleSize,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun FirstCircle(
    isDayMode: Boolean,
    modifier: Modifier = Modifier
) {
    val sunColor = remember { Brush.linearGradient(listOf(Color(0XFFED4868), Color(0XFFE58C39))) }
    val moonColor = remember { Brush.linearGradient(listOf(Color(0XFF7D6EF2), Color(0XFF92B3F0))) }

    val circleColor = remember(isDayMode) {
        if (isDayMode) sunColor else moonColor
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = circleColor,
                shape = CircleShape,
            )
    )
}

@Composable
private fun SecondCircle(
    isDayMode: Boolean,
    secondCircleSize: Dp,
    modifier: Modifier
) {
    val circleSize: Dp by animateDpAsState(
        if (isDayMode) 0.dp else secondCircleSize,
        animationSpec = tween(400)
    )

    Box(
        modifier = modifier
            .absoluteOffset(x = 10.dp)
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .size(circleSize)
    )
}