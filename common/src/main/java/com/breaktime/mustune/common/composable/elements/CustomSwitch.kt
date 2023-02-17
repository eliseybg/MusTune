package com.breaktime.mustune.common.composable.elements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.breaktime.mustune.common.extentions.dpToPx

@Composable
fun CustomSwitch(
    width: Dp = 48.dp,
    height: Dp = 24.dp,
    checkedTrackColor: Color = Color(0xFF0F235E),
    uncheckedTrackColor: Color = Color(0xFFEAEAEA),
    checkedThumbColor: Color = Color.White,
    uncheckedThumbColor: Color = Color.White,
    thumbRadius: Dp = 10.dp,
    gapBetweenThumbAndTrackEdge: Dp = 2.dp,
    cornerRadius: Dp = 11.dp
) {
    var isSwitchEnabled by remember { mutableStateOf(false) }

    val animatePosition = animateFloatAsState(
        targetValue = if (isSwitchEnabled)
            (width - gapBetweenThumbAndTrackEdge - thumbRadius).dpToPx()
        else
            (gapBetweenThumbAndTrackEdge + thumbRadius).dpToPx()
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        isSwitchEnabled = !isSwitchEnabled
                    }
                )
            }
    ) {
        drawRoundRect(
            color = if (isSwitchEnabled) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = cornerRadius.toPx(), y = cornerRadius.toPx()),
            style = Fill
        )

        drawCircle(
            color = if (isSwitchEnabled) checkedThumbColor else uncheckedThumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }
}

@Preview
@Composable
fun CustomSwitchPreview() {
    CustomSwitch()
}