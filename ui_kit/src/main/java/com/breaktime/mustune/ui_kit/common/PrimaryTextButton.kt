package com.breaktime.mustune.ui_kit.common

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
fun PrimaryTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    cornersShape: Shape = RoundedCornerShape(10.dp),
    colors: PrimaryTextButtonColors = PrimaryTextButtonDefaults.primaryTextButtonColors(),
    sizes: PrimaryTextButtonSizes = PrimaryTextButtonDefaults.primaryTextButtonSizes()
) {
    Button(
        modifier = modifier
            .height(sizes.textButtonHeight)
            .fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 4.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors.enabledButtonColor,
            disabledBackgroundColor = colors.disabledButtonColor,
            contentColor = colors.enabledTextColor,
            disabledContentColor = colors.disabledTextColor
        ),
        shape = cornersShape
    ) {
        Text(text = text, fontSize = sizes.fontSize)
    }
}

@Preview
@Composable
fun CustomTextButtonEnabledPreview() {
    PrimaryTextButton(
        text = "text",
        onClick = { },
        enabled = true
    )
}

@Preview
@Composable
fun CustomTextButtonDisabledPreview() {
    PrimaryTextButton(
        text = "text",
        onClick = { },
        enabled = false
    )
}

object PrimaryTextButtonDefaults {
    @Composable
    fun primaryTextButtonColors(
        enabledButtonColor: Color = MusTuneTheme.colors.primary,
        disabledButtonColor: Color = MusTuneTheme.colors.secondary,
        enabledTextColor: Color = MusTuneTheme.colors.onPrimary,
        disabledTextColor: Color = MusTuneTheme.colors.content
    ) = PrimaryTextButtonColors(
        enabledButtonColor,
        disabledButtonColor,
        enabledTextColor,
        disabledTextColor
    )

    @Composable
    fun primaryTextButtonSizes(
        textButtonHeight: Dp = 46.dp,
        fontSize: TextUnit = 16.sp
    ) = PrimaryTextButtonSizes(textButtonHeight, fontSize)
}

data class PrimaryTextButtonColors(
    val enabledButtonColor: Color,
    val disabledButtonColor: Color,
    val enabledTextColor: Color,
    val disabledTextColor: Color
)

data class PrimaryTextButtonSizes(
    val textButtonHeight: Dp = 46.dp,
    val fontSize: TextUnit = 16.sp
)