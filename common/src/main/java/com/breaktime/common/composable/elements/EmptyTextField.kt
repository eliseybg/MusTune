package com.breaktime.common.composable.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.common.extentions.visible

@Composable
fun EmptyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    singleLine: Boolean = false,
    colors: EmptyTextFieldColors = EmptyTextFieldDefaults.emptyTextFieldColors(),
    sizes: EmptyTextFieldSizes = EmptyTextFieldDefaults.emptyTextFieldSizes()
) {
    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        Text(
            modifier = Modifier
                .padding(sizes.innerHintPadding)
                .visible(value.isEmpty()),
            text = hint,
            fontSize = sizes.hintFontSize,
            color = colors.hintColor
        )
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(sizes.innerTextFieldPadding),
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = LocalTextStyle.current.copy(
                color = colors.textColor,
                fontSize = sizes.textFieldFontSize
            )
        )
    }
}

@Preview
@Composable
internal fun EmptyTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    EmptyTextField(value = text, onValueChange = { text = it }, hint = "hint")
}

object EmptyTextFieldDefaults {
    fun emptyTextFieldColors(
        textColor: Color = Color.Black,
        hintColor: Color = Color.Gray
    ) = EmptyTextFieldColors(textColor, hintColor)

    fun emptyTextFieldSizes(
        innerTextFieldPadding: PaddingValues = PaddingValues(0.dp),
        innerHintPadding: PaddingValues = PaddingValues(0.dp),
        textFieldFontSize: TextUnit = 16.sp,
        hintFontSize: TextUnit = 14.sp
    ) = EmptyTextFieldSizes(
        innerTextFieldPadding,
        innerHintPadding,
        textFieldFontSize,
        hintFontSize
    )
}

data class EmptyTextFieldColors(
    val textColor: Color = Color.Black,
    val hintColor: Color = Color.Gray
)

data class EmptyTextFieldSizes(
    val innerTextFieldPadding: PaddingValues = PaddingValues(0.dp),
    val innerHintPadding: PaddingValues = PaddingValues(0.dp),
    val textFieldFontSize: TextUnit = 16.sp,
    val hintFontSize: TextUnit = 14.sp
)
