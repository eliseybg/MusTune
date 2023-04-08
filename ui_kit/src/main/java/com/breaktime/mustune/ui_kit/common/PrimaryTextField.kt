package com.breaktime.mustune.ui_kit.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.resources.theme.MusTuneTheme

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearedClick: (() -> Unit)? = null,
    hint: String = "",
    singleLine: Boolean = true,
    showUnderline: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    focusRequester: FocusRequester = FocusRequester(),
    colors: PrimaryTextFieldColors = PrimaryTextFieldDefaults.primaryTextFieldColors(),
    sizes: PrimaryTextFieldSizes = PrimaryTextFieldDefaults.primaryTextFieldSizes()
) {
    var hasFocus by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Box(contentAlignment = Alignment.CenterStart) {
            if (value.isEmpty()) Text(
                modifier = Modifier.padding(sizes.innerHintPadding),
                text = hint,
                fontSize = sizes.hintFontSize,
                color = colors.hintColor
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(sizes.textFieldPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                BasicTextField(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                        .onFocusChanged { hasFocus = it.hasFocus },
                    value = value,
                    visualTransformation = visualTransformation,
                    keyboardOptions = keyboardOptions,
                    onValueChange = { onValueChange(if (singleLine) it.replace("\n", "") else it) },
                    singleLine = singleLine,
                    textStyle = LocalTextStyle.current.copy(
                        color = colors.textColor,
                        fontSize = sizes.textFieldFontSize
                    )
                )

                if (value.isNotEmpty()) Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .clickable { onClearedClick?.invoke() },
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear icon"
                )
            }
        }
        if (showUnderline) Divider(
            modifier = Modifier.padding(top = 3.dp),
            color = if (hasFocus) MusTuneTheme.colors.primary else MusTuneTheme.colors.divider,
            thickness = if (hasFocus) 2.dp else 1.dp
        )
    }
}

@Preview
@Composable
internal fun PrimaryTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    PrimaryTextField(
        value = text,
        onValueChange = { text = it },
        onClearedClick = { text = "" },
        hint = "hint"
    )
}

object PrimaryTextFieldDefaults {
    @Composable
    fun primaryTextFieldColors(
        textColor: Color = MusTuneTheme.colors.content,
        hintColor: Color = MusTuneTheme.colors.textHint
    ) = PrimaryTextFieldColors(textColor, hintColor)

    @Composable
    fun primaryTextFieldSizes(
        innerHintPadding: PaddingValues = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
        textFieldPadding: PaddingValues = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
        textFieldFontSize: TextUnit = 16.sp,
        hintFontSize: TextUnit = 14.sp
    ) = PrimaryTextFieldSizes(innerHintPadding, textFieldPadding, textFieldFontSize, hintFontSize)
}

data class PrimaryTextFieldColors(
    val textColor: Color,
    val hintColor: Color
)

data class PrimaryTextFieldSizes(
    val innerHintPadding: PaddingValues = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
    val textFieldPadding: PaddingValues = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
    val textFieldFontSize: TextUnit = 16.sp,
    val hintFontSize: TextUnit = 14.sp
)