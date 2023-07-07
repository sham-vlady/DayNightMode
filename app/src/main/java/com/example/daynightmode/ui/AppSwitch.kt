package com.example.daynightmode.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val SwitchWidth = 72.dp
private val SwitchHeight = 40.dp

@Composable
fun AppSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    strokeWidth: Dp = 2.dp,
    checkedTrackColor: Color = MaterialTheme.colors.onBackground,
    uncheckedTrackColor: Color = MaterialTheme.colors.onBackground,
    gapBetweenThumbAndTrackEdge: Dp = 4.dp,
    modifier: Modifier = Modifier
) {
    val thumbRadius = remember { (SwitchHeight / 2) - gapBetweenThumbAndTrackEdge }
    val animatePosition = animateFloatAsState(
        targetValue = if (checked) {
            with(LocalDensity.current) { (SwitchWidth - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        } else {
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
        }
    )

    Box(modifier = modifier
        .size(width = SwitchWidth, height = SwitchHeight)
        .pointerInput(checked) {
            detectTapGestures(
                onTap = { onCheckedChange(checked.not()) }
            )
        }
        .drawWithCache {
            onDrawBehind {
                drawRoundRect(
                    color = if (checked) checkedTrackColor else uncheckedTrackColor,
                    cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx()),
                    style = Stroke(width = strokeWidth.toPx())
                )

                drawCircle(
                    color = if (checked) checkedTrackColor else uncheckedTrackColor,
                    radius = thumbRadius.toPx(),
                    center = Offset(
                        x = animatePosition.value,
                        y = size.height / 2
                    )
                )
            }
        }
    )
}