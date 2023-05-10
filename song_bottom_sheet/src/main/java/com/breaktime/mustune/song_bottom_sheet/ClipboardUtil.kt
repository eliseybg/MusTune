package com.breaktime.mustune.song_bottom_sheet

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import javax.inject.Inject

class ClipboardUtil @Inject constructor(private val context: Context) {
    fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
    }
}