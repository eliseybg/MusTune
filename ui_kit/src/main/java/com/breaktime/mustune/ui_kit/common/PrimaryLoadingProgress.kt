package com.breaktime.mustune.ui_kit.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.breaktime.mustune.resources.theme.MusTuneTheme

@Composable
fun PrimaryLoadingProgress(
    modifier: Modifier = Modifier,
    cornersShape: Shape = RoundedCornerShape(20.dp),
    colors: PrimaryLoadingProgressColors = PrimaryLoadingProgressDefaults.primaryLoadingProgressColors(),
    sizes: PrimaryLoadingProgressSizes = PrimaryLoadingProgressDefaults.primaryLoadingProgressSizes()
) {
    Popup {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MusTuneTheme.colors.shadowBackground),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = modifier.size(sizes.size),
                shape = cornersShape,
                backgroundColor = Color.White,
                elevation = 5.dp
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(sizes.innerPadding),
                    color = colors.progressColor
                )
            }
        }
    }
}

@Preview
@Composable
fun PrimaryLoadingProgressPreview() {
    PrimaryLoadingProgress()
}

object PrimaryLoadingProgressDefaults {
    @Composable
    fun primaryLoadingProgressColors(
        backgroundColor: Color = MusTuneTheme.colors.background,
        progressColor: Color = MusTuneTheme.colors.primary
    ) = PrimaryLoadingProgressColors(backgroundColor, progressColor)

    @Composable
    fun primaryLoadingProgressSizes(
        size: Dp = 90.dp,
        innerPadding: Dp = 16.dp
    ) = PrimaryLoadingProgressSizes(size, innerPadding)
}

data class PrimaryLoadingProgressColors(
    val backgroundColor: Color,
    val progressColor: Color
)

data class PrimaryLoadingProgressSizes(
    val size: Dp = 90.dp,
    val innerPadding: Dp = 16.dp
)