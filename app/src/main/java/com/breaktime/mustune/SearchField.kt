package com.breaktime.mustune

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    searchText: MutableState<String>,
    isFilterActive: Boolean,
    onFilterClick: () -> Unit
) {
    Row(
        modifier = modifier
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(8.dp)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search icon"
        )
        BasicTextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 10.dp),
            value = searchText.value,
            onValueChange = { searchText.value = it },
            singleLine = true
        )
        if (searchText.value.isNotEmpty())
            Icon(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(16.dp)
                    .clickable { searchText.value = "" },
                imageVector = Icons.Default.Clear,
                contentDescription = "clear icon"
            )
        Icon(
            modifier = Modifier.clickable { onFilterClick() },
            painter = if (isFilterActive) painterResource(id = R.drawable.ic_sliders_filled)
            else painterResource(id = R.drawable.ic_sliders_outlined),
            contentDescription = "filter icon",
            tint = if (isFilterActive) Color.Black else Color.Black.copy(alpha = 0.8f)
        )
    }
}

@Preview
@Composable
fun SearchFieldPreview() {
    val searchText = remember { mutableStateOf("") }
    var isFilterActive by remember { mutableStateOf(false) }
    var showFilter by remember { mutableStateOf(false) }
    val blur = animateFloatAsState(targetValue = if (showFilter) 8f else 0f)

    Column {
        SearchField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            searchText = searchText,
            isFilterActive = isFilterActive,
            onFilterClick = { showFilter = !showFilter }
        )
        Box {
            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
            Box(modifier = Modifier.blur(blur.value.dp)) {
                LazyColumn(userScrollEnabled = !showFilter) {
                    itemsIndexed(list) { index, item ->
                        MusicItem(
                            title = "Title",
                            description = "Description",
                            onItemClick = {},
                            onMoreClick = {}
                        )
                        if (index < list.lastIndex)
                            Divider(
                                color = Color(0xFFD6D6D6),
                                thickness = 1.dp,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .zIndex(20f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .zIndex(20f)
                        .animateContentSize()
                ) {
                    var isFilter1Selected by remember { mutableStateOf(false) }
                    var isFilter2Selected by remember { mutableStateOf(false) }
                    var isFilter3Selected by remember { mutableStateOf(false) }
                    var isFilter4Selected by remember { mutableStateOf(false) }

                    LaunchedEffect(
                        isFilter1Selected,
                        isFilter2Selected,
                        isFilter3Selected,
                        isFilter4Selected
                    ) {
                        isFilterActive =
                            isFilter1Selected || isFilter2Selected || isFilter3Selected || isFilter4Selected
                    }
                    if (showFilter) Row(
                        modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isFilter1Selected,
                            onCheckedChange = { isFilter1Selected = it }
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = { isFilter1Selected = !isFilter1Selected }
                                ),
                            text = "filter 1"
                        )
                    }
                    if (showFilter) Row(
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isFilter2Selected,
                            onCheckedChange = { isFilter2Selected = it }
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = { isFilter2Selected = !isFilter2Selected }
                                ),
                            text = "filter 2"
                        )
                    }
                    if (showFilter) Row(
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isFilter3Selected,
                            onCheckedChange = { isFilter3Selected = it }
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = { isFilter3Selected = !isFilter3Selected }
                                ),
                            text = "filter 3"
                        )
                    }
                    if (showFilter) Row(
                        modifier = Modifier.padding(bottom = 8.dp, start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isFilter4Selected,
                            onCheckedChange = { isFilter4Selected = it }
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = { isFilter4Selected = !isFilter4Selected }
                                ),
                            text = "filter 4"
                        )
                    }
                }
            }
        }
    }

    if (showFilter) Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.2f * blur.value / 8f))
            .zIndex(2f)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { showFilter = false }
            )
    )
}