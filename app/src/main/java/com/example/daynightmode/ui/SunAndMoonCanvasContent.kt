package com.example.daynightmode.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp

@Composable
fun SunAndMoonCanvasContent(
    isDayMode: Boolean
) {
    val secondCircleColor = MaterialTheme.colors.background

    val circleSize: Float by animateFloatAsState(
        if (isDayMode) 0f else 1f,
        animationSpec = tween(400)
    )

    val circleCenter: Float by animateFloatAsState(
        if (isDayMode) 1f else 0.7f,
        animationSpec = tween(400)
    )

    Box(modifier = Modifier
        .size(100.dp)
        .drawWithCache {
            val firstCircleRadius = size.width / 2
            val secondCircleRadius = firstCircleRadius * 0.8f

            val rotateAngle = -40f
            val translateOffset = firstCircleRadius * circleCenter

            val sunColor = Brush.linearGradient(
                colors = listOf(Color(0XFFED4868), Color(0XFFE58C39)),
                start = Offset(0f, size.height),
                end = Offset(size.width, 0f)
            )
            val moonColor = Brush.linearGradient(
                colors = listOf(Color(0XFF7D6EF2), Color(0XFF92B3F0)),
                start = Offset(0f, size.height),
                end = Offset(size.width, 0f)
            )

            val circleColor = if (isDayMode) sunColor else moonColor

            onDrawBehind {
                drawCircle(
                    brush = circleColor,
                    radius = firstCircleRadius
                )

                withTransform({
                    rotate(rotateAngle)
                    translate(translateOffset)
                    scale(circleSize)
                }) {
                    drawCircle(
                        color = secondCircleColor,
                        radius = secondCircleRadius
                    )
                }
            }
        }
    )
}