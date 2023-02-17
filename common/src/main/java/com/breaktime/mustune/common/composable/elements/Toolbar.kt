package com.breaktime.mustune.common.composable.elements

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String,
    navigation: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .shadow(
                color = Color.Black.copy(0.25f),
                blurRadius = 4.dp,
                spread = 1.5.dp,
                offsetY = 50.dp
            )
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) { navigation() }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Box(modifier = Modifier.align(Alignment.CenterEnd)) { actions() }
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    Toolbar(
        title = "Title",
        navigation = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        }
    )
}

@Preview
@Composable
fun ToolbarTitlePreview() {
    Toolbar(title = "Title")
}

@Preview
@Composable
fun ToolbarNavigationPreview() {
    Toolbar(
        title = "Title",
        navigation = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
            )
        }
    )
}

@Preview
@Composable
fun ToolbarActionPreview() {
    Toolbar(
        title = "Title",
        actions = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        }
    )
}

fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
    modifier: Modifier = Modifier
) = then(modifier.drawBehind {
    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        val spreadPixel = spread.toPx()
        val leftPixel = (0f - spreadPixel) + offsetX.toPx()
        val topPixel = (0f - spreadPixel) + offsetY.toPx()
        val rightPixel = size.width + spreadPixel
        val bottomPixel = size.height + spreadPixel

        if (blurRadius != 0.dp) frameworkPaint.maskFilter = BlurMaskFilter(
            blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL
        )

        frameworkPaint.color = color.toArgb()
        it.drawRoundRect(
            left = leftPixel,
            top = topPixel,
            right = rightPixel,
            bottom = bottomPixel,
            radiusX = borderRadius.toPx(),
            radiusY = borderRadius.toPx(),
            paint
        )
    }
})