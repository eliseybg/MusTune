package com.breaktime.mustune.ui_kit.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.resources.theme.MusTuneTheme

@Composable
fun PrimaryChipButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    cornersShape: Shape = RoundedCornerShape(10.dp),
    colors: PrimaryChipButtonColors = PrimaryChipButtonDefaults.primaryChipButtonColors(),
    sizes: PrimaryChipButtonSizes = PrimaryChipButtonDefaults.primaryChipButtonSizes()
) {
    Button(
        modifier = modifier
            .height(sizes.chipButtonHeight)
            .border(
                width = 1.dp,
                color = if (enabled) colors.enabledButtonStrokeColor else colors.disabledButtonStrokeColor,
                shape = cornersShape
            )
            .fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors.enabledButtonColor,
            disabledBackgroundColor = colors.disabledButtonColor,
            contentColor = colors.enabledTextColor,
            disabledContentColor = colors.disabledTextColor
        ),
        contentPadding = PaddingValues(),
        shape = cornersShape
    ) {
        Text(
            text = text,
            fontSize = sizes.fontSize
        )
    }
}

@Preview
@Composable
fun CustomChipButtonEnabledPreview() {
    PrimaryChipButton(
        text = "text",
        onClick = { },
        enabled = true
    )
}

@Preview
@Composable
fun CustomChipButtonDisabledPreview() {
    PrimaryChipButton(
        text = "text",
        onClick = { },
        enabled = false
    )
}

object PrimaryChipButtonDefaults {
    @Composable
    fun primaryChipButtonColors(
        enabledButtonColor: Color = MusTuneTheme.colors.onPrimary,
        disabledButtonColor: Color = MusTuneTheme.colors.secondary.copy(alpha = 0.6f),
        enabledButtonStrokeColor: Color = MusTuneTheme.colors.primary,
        disabledButtonStrokeColor: Color = MusTuneTheme.colors.secondary,
        enabledTextColor: Color = MusTuneTheme.colors.primary,
        disabledTextColor: Color = MusTuneTheme.colors.textHint
    ) = PrimaryChipButtonColors(
        enabledButtonColor,
        disabledButtonColor,
        enabledButtonStrokeColor,
        disabledButtonStrokeColor,
        enabledTextColor,
        disabledTextColor
    )

    @Composable
    fun primaryChipButtonSizes(
        chipButtonHeight: Dp = 30.dp,
        fontSize: TextUnit = 16.sp
    ) = PrimaryChipButtonSizes(chipButtonHeight, fontSize)
}

data class PrimaryChipButtonColors(
    val enabledButtonColor: Color,
    val disabledButtonColor: Color,
    val enabledButtonStrokeColor: Color,
    val disabledButtonStrokeColor: Color,
    val enabledTextColor: Color,
    val disabledTextColor: Color
)

data class PrimaryChipButtonSizes(
    val chipButtonHeight: Dp = 30.dp,
    val fontSize: TextUnit = 16.sp
)