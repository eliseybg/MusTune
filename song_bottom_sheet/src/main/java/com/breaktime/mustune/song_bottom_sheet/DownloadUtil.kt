package com.breaktime.mustune.song_bottom_sheet

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.breaktime.mustune.common.Constants
import javax.inject.Inject

class DownloadUtil @Inject constructor(private val context: Context) {
    fun downloadSong(songId: String) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?

        val url = Constants.BASE_URL + "/downloadSong?songId=$songId"
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("My File")
            .setDescription("Downloading...")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "filename")

        downloadManager?.enqueue(request)
    }
}