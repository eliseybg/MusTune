package com.breaktime.mustune.session_manager.api

import androidx.compose.runtime.compositionLocalOf
import com.breaktime.mustune.common.provider.TokenProvider

interface SessionManagerProvider {
    val sessionManager: SessionManager
    val tokenProvider: TokenProvider
}

val LocalSessionManagerProvider = compositionLocalOf<SessionManagerProvider> { error("No data provider found!") }