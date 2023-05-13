package com.breaktime.mustune.common.extentions

import java.io.Closeable
import java.io.InputStream
import java.io.OutputStream

fun InputStream.copyBufferedTo(output: OutputStream): Closeable {
    output.buffered().use { copyTo(it) }
    return Closeable {
        close()
        output.close()
    }
}
