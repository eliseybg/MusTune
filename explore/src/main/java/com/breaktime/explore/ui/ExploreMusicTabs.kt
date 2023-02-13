package com.breaktime.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ExploreMusicTabs(
    modifier: Modifier = Modifier,
    tabsNames: List<MusicTab>,
    currentTab: Int,
    onChangeTab: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = currentTab,
        backgroundColor = Color(0xFFEAEAEA),
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(5.dp)),
        indicator = {},
    ) {
        tabsNames.forEachIndexed { index, tab ->
            val selected = currentTab == index
            Tab(
                modifier = Modifier
                    .height(32.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(if (selected) Color(0xFF0F235E) else Color.Transparent),
                selected = selected,
                onClick = {
                    onChangeTab(index)
                },
                text = {
                    Text(
                        text = tab.name,
                        color = if (selected) Color.White else Color.Black,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun ExploreMusicTabPreview() {
    val musicTabs = listOf(MusicTab.Explore, MusicTab.Favorite)
    var currentTab by remember { mutableStateOf(0) }

    ExploreMusicTabs(
        tabsNames = musicTabs,
        currentTab = currentTab,
        onChangeTab = { currentTab = it }
    )
}

sealed class MusicTab(val name: String) {
    object Explore : MusicTab("explore")
    object Favorite : MusicTab("favorite")
}