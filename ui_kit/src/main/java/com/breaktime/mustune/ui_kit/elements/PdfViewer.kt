package com.breaktime.mustune.ui_kit.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pdfview.PDFView
import java.io.File

@Composable
fun PdfViewer(
    modifier: Modifier = Modifier,
    file: File
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            PDFView(context, null)
        },
        update = { pdfView ->
            pdfView.fromFile(file).show()
        }
    )
}