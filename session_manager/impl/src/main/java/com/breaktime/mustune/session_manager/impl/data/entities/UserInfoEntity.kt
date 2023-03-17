package com.breaktime.mustune.session_manager.impl.data.entities

import com.breaktime.mustune.session_manager.api.models.UserInfo
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoEntity(val username: String, val email: String, val token: String)

fun UserInfoEntity.toUserInfo(): UserInfo {
    return UserInfo(username, email)
}