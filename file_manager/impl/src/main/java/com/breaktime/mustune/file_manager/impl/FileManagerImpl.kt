package com.breaktime.mustune.file_manager.impl

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.breaktime.mustune.file_manager.api.FileManager
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class FileManagerImpl @Inject constructor(private val context: Context) : FileManager {
    override fun getFileName(uri: Uri): String {
        return context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(nameIndex)
        } ?: "Fail loading name"
    }

    override fun getTempFile(uri: Uri): File? {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val file = File(getTempFolder().path + getFileName(uri)).also {
            it.createNewFile()
        }
        inputStream.copyBufferedTo(file.outputStream()).close()
        return file
    }

    private fun getTempFolder(): File {
        val imageFile = File(context.externalCacheDir, "temp" + "/")
        imageFile.parentFile?.mkdirs()
        return imageFile
    }

    private fun InputStream.copyBufferedTo(os: OutputStream) = this.apply {
        os.buffered().use { copyTo(it) }
    }
}