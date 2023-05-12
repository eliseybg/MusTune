package com.breaktime.mustune.song_bottom_sheet

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.provider.TokenProvider
import javax.inject.Inject

class DownloadUtil @Inject constructor(
    private val context: Context,
    private val tokenProvider: TokenProvider
) {
    fun downloadSong(songId: String, title: String, artist: String) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
        val token = tokenProvider.getToken()

        val url = Constants.BASE_URL + "/downloadSong?songId=$songId"
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("$title - $artist")
            .addRequestHeader("Authorization", "Bearer $token")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE or DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "filename")

        downloadManager?.enqueue(request)
    }
}