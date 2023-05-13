package com.breaktime.mustune.file_manager.api

import android.net.Uri
import java.io.File

interface FileManager {
    fun getFileName(uri: Uri): String

    fun getTempFile(uri: Uri): File?

    fun getSongsDir(): File?

    fun getPdfDir(): File?
}