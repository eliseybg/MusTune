package com.breaktime.mustune.session_manager.impl.data.source

import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity

interface DataSource {
    suspend fun setUserInfo(userInfoEntity: UserInfoEntity)
    suspend fun getUserInfo(): UserInfoEntity?
    suspend fun removeUserInfo()
}