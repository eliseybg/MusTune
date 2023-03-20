package com.breaktime.mustune.ui_kit

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.common.extentions.pxToDp

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit = {},
    navigation: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    var navigationWidth by remember { mutableStateOf(0) }
    var actionsWidth by remember { mutableStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                color = Color.Black.copy(0.25f),
                blurRadius = 4.dp,
                spread = 1.5.dp,
                offsetY = 50.dp
            )
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .onPlaced { navigationWidth = it.size.width }
            ) { navigation() }
            Box(modifier = Modifier.align(Alignment.Center)) {
                content(
                    PaddingValues(
                        start = navigationWidth.pxToDp(),
                        end = actionsWidth.pxToDp()
                    )
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .onPlaced { actionsWidth = it.size.width }
            ) { actions() }
        }
        bottomContent()
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    Toolbar(
        content = {
            Text(
                text = "Title",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
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
    Toolbar(
        content = {
            Text(
                text = "Title",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    )
}

@Preview
@Composable
fun ToolbarNavigationPreview() {
    Toolbar(
        content = {
            Text(
                text = "Title",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
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
        content = {
            Text(
                text = "Title",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
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

private fun Modifier.shadow(
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