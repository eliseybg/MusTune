package com.breaktime.mustune.ui_kit.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme

@Composable
fun PrimaryCheckbox(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    colors: PrimaryCheckboxColors = PrimaryCheckboxDefaults.primaryCheckboxColors(),
    sizes: PrimaryCheckboxSizes = PrimaryCheckboxDefaults.primaryCheckboxSizes()
) {
    Row(
        modifier = modifier.clickable { onCheckedChange?.invoke(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = if (checked) R.drawable.ic_checked else R.drawable.ic_unchecked),
            contentDescription = "checkbox",
            tint = if (checked) colors.checkedColor else colors.uncheckedColor
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            text = text,
            fontSize = sizes.fontSize,
            color = colors.textColor
        )
    }
}

@Preview
@Composable
fun CustomCheckboxPreview() {
    var isCheckboxEnabled by remember { mutableStateOf(false) }
    PrimaryCheckbox(
        checked = isCheckboxEnabled,
        onCheckedChange = { isCheckboxEnabled = it },
        text = "text"
    )
}

object PrimaryCheckboxDefaults {
    @Composable
    fun primaryCheckboxColors(
        checkedColor: Color = MusTuneTheme.colors.primary,
        uncheckedColor: Color = MusTuneTheme.colors.secondary,
        textColor: Color = MusTuneTheme.colors.content,
    ) = PrimaryCheckboxColors(checkedColor, uncheckedColor, textColor)

    @Composable
    fun primaryCheckboxSizes(
        checkboxWidth: Dp = 24.dp,
        checkboxHeight: Dp = 24.dp,
        fontSize: TextUnit = 16.sp
    ) = PrimaryCheckboxSizes(checkboxWidth, checkboxHeight, fontSize)
}

data class PrimaryCheckboxColors(
    val checkedColor: Color,
    val uncheckedColor: Color,
    val textColor: Color
)

data class PrimaryCheckboxSizes(
    val checkboxWidth: Dp = 24.dp,
    val checkboxHeight: Dp = 24.dp,
    val fontSize: TextUnit = 16.sp
)