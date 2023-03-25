package com.breaktime.mustune.ui_kit.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
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
    selected: Boolean,
    onSelect: (isSelected: Boolean) -> Unit,
    cornersShape: Shape = RoundedCornerShape(10.dp),
    colors: PrimaryChipButtonColors = PrimaryChipButtonDefaults.primaryChipButtonColors(),
    sizes: PrimaryChipButtonSizes = PrimaryChipButtonDefaults.primaryChipButtonSizes()
) {
    Box(
        modifier = modifier
            .height(sizes.chipButtonHeight)
            .clip(cornersShape)
            .border(
                width = 1.dp,
                color = if (selected) colors.enabledButtonStrokeColor else colors.disabledButtonStrokeColor,
                shape = cornersShape
            )
            .fillMaxWidth()
            .background(if (selected) colors.enabledButtonColor else colors.disabledButtonColor)
            .clickable { onSelect(!selected) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = sizes.fontSize,
            color = if (selected) colors.enabledTextColor else colors.disabledTextColor,
            fontWeight = if (selected) FontWeight.Bold else null
        )
    }
}

@Preview
@Composable
fun CustomChipButtonEnabledPreview() {
    PrimaryChipButton(
        text = "text",
        onSelect = { },
        selected = true
    )
}

@Preview
@Composable
fun CustomChipButtonDisabledPreview() {
    PrimaryChipButton(
        text = "text",
        onSelect = { },
        selected = false
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