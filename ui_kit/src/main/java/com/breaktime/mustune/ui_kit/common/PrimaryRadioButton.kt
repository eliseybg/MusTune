package com.breaktime.mustune.ui_kit.common

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.resources.theme.MusTuneTheme

@Composable
fun PrimaryRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    colors: PrimaryRadioButtonColors = PrimaryRadioButtonDefaults.primaryRadioButtonColors(),
    sizes: PrimaryRadioButtonSizes = PrimaryRadioButtonDefaults.primaryRadioButtonSizes()
) {
    Row(
        modifier = modifier.clickable { onCheckedChange?.invoke(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(sizes.radioButtonRadius * 2)) {
            drawCircle(
                color = if (checked) colors.checkedColor else colors.uncheckedColor,
                radius = sizes.radioButtonRadius.toPx(),
                center = Offset(
                    x = size.width / 2,
                    y = size.height / 2
                ),
                style = Stroke(width = sizes.radioButtonStroke.toPx())
            )
            if (checked) drawCircle(
                color = colors.checkedColor,
                radius = sizes.thumbRadius.toPx(),
                center = Offset(
                    x = size.width / 2,
                    y = size.height / 2
                ),
                style = Fill
            )
        }
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            text = text,
            fontSize = sizes.fontSize
        )
    }
}

@Preview
@Composable
fun PrimaryRadioButtonPreview() {
    var isRadioButtonEnabled by remember { mutableStateOf(false) }
    PrimaryRadioButton(
        checked = isRadioButtonEnabled,
        onCheckedChange = { isRadioButtonEnabled = it },
        text = "text"
    )
}

object PrimaryRadioButtonDefaults {
    @Composable
    fun primaryRadioButtonColors(
        checkedColor: Color = MusTuneTheme.colors.primary,
        uncheckedColor: Color = MusTuneTheme.colors.secondary,
    ) = PrimaryRadioButtonColors(checkedColor, uncheckedColor)

    @Composable
    fun primaryRadioButtonSizes(
        radioButtonRadius: Dp = 10.dp,
        radioButtonStroke: Dp = 2.dp,
        thumbRadius: Dp = 5.dp,
        fontSize: TextUnit = 16.sp
    ) = PrimaryRadioButtonSizes(
        radioButtonRadius,
        radioButtonStroke,
        thumbRadius,
        fontSize
    )
}

data class PrimaryRadioButtonColors(
    val checkedColor: Color,
    val uncheckedColor: Color,
)

data class PrimaryRadioButtonSizes(
    val radioButtonRadius: Dp = 10.dp,
    val radioButtonStroke: Dp = 2.dp,
    val thumbRadius: Dp = 5.dp,
    val fontSize: TextUnit = 16.sp
)