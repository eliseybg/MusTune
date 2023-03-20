package com.breaktime.mustune.ui_kit

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
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors.enabledButtonColor,
            disabledBackgroundColor = colors.disabledButtonColor
        ),
        shape = cornersShape
    ) {
        Text(
            text = text,
            fontSize = sizes.fontSize,
            color = if (enabled) colors.enabledTextColor else colors.disabledTextColor
        )
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
        enabledButtonColor: Color = Color(0xFF0F235E),
        disabledButtonColor: Color = Color(0xFFEAEAEA),
        enabledTextColor: Color = Color.White,
        disabledTextColor: Color = Color.Black
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