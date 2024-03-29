package com.breaktime.mustune.common.extentions

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.paging.compose.LazyPagingItems

@Preview(device = "id:Nexus One")
@Preview(device = "id:Nexus S")
@Preview(device = "id:Galaxy Nexus")
@Preview(device = "id:Nexus 4")
@Preview(device = "id:Nexus 5")
@Preview(device = "id:pixel")
@Preview(device = "id:pixel_2")
@Preview(device = "id:Nexus 5X")
@Preview(device = "id:pixel_3")
@Preview(device = "id:pixel_xl")
@Preview(device = "id:pixel_3a")
@Preview(device = "id:Nexus 6P")
@Preview(device = "id:pixel_4")
@Preview(device = "id:pixel_4a")
@Preview(device = "id:Nexus 6")
@Preview(device = "id:pixel_2_xl")
@Preview(device = "id:pixel_3a_xl")
@Preview(device = "id:pixel_5")
@Preview(device = "id:pixel_3_xl")
@Preview(device = "id:pixel_4_xl")
@Preview(device = "id:pixel_6")
@Preview(device = "id:pixel_6_pro")
annotation class AllPhoneScreensPreview

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun Number.pxToDp() = with(LocalDensity.current) { this@pxToDp.toFloat().toDp() }

@Composable
fun <T : Any> LazyPagingItems<T>.rememberLazyListState(): LazyListState {
    return when (itemCount) {
        0 -> remember(this) { LazyListState(0, 0) }
        else -> androidx.compose.foundation.lazy.rememberLazyListState()
    }
}