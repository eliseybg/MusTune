package com.breaktime.mustune.ui_kit

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.common.extentions.dpToPx

@Composable
fun PrimarySwitch(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    colors: PrimarySwitchColors = PrimarySwitchDefaults.primarySwitchColors(),
    sizes: PrimarySwitchSizes = PrimarySwitchDefaults.primarySwitchSizes()
) {
    val animatePosition = animateFloatAsState(
        targetValue = if (checked) (sizes.switchWidth - sizes.gapBetweenThumbAndTrackEdge - sizes.thumbRadius).dpToPx()
        else (sizes.gapBetweenThumbAndTrackEdge + sizes.thumbRadius).dpToPx(),
        label = "position animation"
    )

    Row(
        modifier = Modifier.clickable { onCheckedChange?.invoke(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            text = text,
            fontSize = sizes.fontSize
        )
        Canvas(modifier = modifier.size(width = sizes.switchWidth, height = sizes.switchHeight)) {
            drawRoundRect(
                color = if (checked) colors.checkedTrackColor else colors.uncheckedTrackColor,
                cornerRadius = CornerRadius(
                    x = sizes.switchCornerRadius.toPx(),
                    y = sizes.switchCornerRadius.toPx()
                ),
                style = Fill
            )

            drawCircle(
                color = if (checked) colors.checkedThumbColor else colors.uncheckedThumbColor,
                radius = sizes.thumbRadius.toPx(),
                center = Offset(
                    x = animatePosition.value,
                    y = size.height / 2
                )
            )
        }
    }
}

@Preview
@Composable
fun CustomSwitchPreview() {
    var isSwitchEnabled by remember { mutableStateOf(false) }
    PrimarySwitch(
        checked = isSwitchEnabled,
        onCheckedChange = { isSwitchEnabled = it },
        text = "text"
    )
}

object PrimarySwitchDefaults {
    @Composable
    fun primarySwitchColors(
        checkedTrackColor: Color = Color(0xFF0F235E),
        uncheckedTrackColor: Color = Color(0xFFEAEAEA),
        checkedThumbColor: Color = Color.White,
        uncheckedThumbColor: Color = checkedThumbColor
    ) = PrimarySwitchColors(
        checkedTrackColor,
        uncheckedTrackColor,
        checkedThumbColor,
        uncheckedThumbColor
    )

    @Composable
    fun primarySwitchSizes(
        switchWidth: Dp = 48.dp,
        switchHeight: Dp = 24.dp,
        thumbRadius: Dp = 10.dp,
        gapBetweenThumbAndTrackEdge: Dp = switchHeight / 2 - thumbRadius,
        switchCornerRadius: Dp = switchHeight / 2,
        fontSize: TextUnit = 16.sp
    ) = PrimarySwitchSizes(
        switchWidth,
        switchHeight,
        thumbRadius,
        gapBetweenThumbAndTrackEdge,
        switchCornerRadius,
        fontSize
    )
}

data class PrimarySwitchColors(
    val checkedTrackColor: Color,
    val uncheckedTrackColor: Color,
    val checkedThumbColor: Color,
    val uncheckedThumbColor: Color
)

data class PrimarySwitchSizes(
    val switchWidth: Dp = 48.dp,
    val switchHeight: Dp = 24.dp,
    val thumbRadius: Dp = 10.dp,
    val gapBetweenThumbAndTrackEdge: Dp = switchHeight / 2 - thumbRadius,
    val switchCornerRadius: Dp = switchHeight / 2,
    val fontSize: TextUnit = 16.sp
)