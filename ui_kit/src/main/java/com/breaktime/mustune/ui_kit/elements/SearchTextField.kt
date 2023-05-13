package com.breaktime.mustune.ui_kit.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.ui_kit.common.PrimaryTextField

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchText: String,
    hint: String = "",
    onChangeSearchText: (String) -> Unit,
    onClearedClick: () -> Unit = {},
    focusRequester: FocusRequester = FocusRequester()
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(MusTuneTheme.colors.secondary)
            .height(40.dp)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search icon",
            tint = MusTuneTheme.colors.content
        )
        PrimaryTextField(
            value = searchText,
            onValueChange = onChangeSearchText,
            onClearedClick = onClearedClick,
            hint = hint,
            showUnderline = false,
            focusRequester = focusRequester
        )
    }
}

@Preview
@Composable
internal fun SearchTextFieldPreview() {
    var searchText by remember { mutableStateOf("") }
    SearchTextField(
        searchText = searchText,
        onChangeSearchText = { searchText = it },
        onClearedClick = { searchText = "" }
    )
}