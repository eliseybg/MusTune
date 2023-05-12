package com.breaktime.mustune.common.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

enum class Orientation(internal val value: Int) {
    PORTRAIT(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT),
    LANDSCAPE(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
}

@SuppressLint("ComposableNaming")
@Composable
fun setScreenOrientation(orientation: Orientation) {
    val context = LocalContext.current
    LaunchedEffect(orientation) {
        context.setScreenOrientation(orientation)
    }
}

fun Context.setScreenOrientation(orientation: Orientation) {
    val activity = findActivity() ?: return
    activity.requestedOrientation = orientation.value
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}