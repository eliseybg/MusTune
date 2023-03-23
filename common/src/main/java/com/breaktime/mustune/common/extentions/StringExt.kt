package com.breaktime.mustune.common.extentions

fun String.filterUsername() = replace(Regex("[^A-Za-z0-9]"), "").lowercase()