package com.breaktime.mustune.file_manager.api

import androidx.compose.runtime.compositionLocalOf

interface FileManagerProvider {
    val fileManager: FileManager
}

val LocalFileManagerProvider = compositionLocalOf<FileManagerProvider> { error("No data provider found!") }