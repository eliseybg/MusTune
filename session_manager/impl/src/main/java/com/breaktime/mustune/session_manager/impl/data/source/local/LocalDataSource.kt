package com.breaktime.mustune.session_manager.impl.data.source.local

import android.content.Context
import androidx.datastore.dataStore
import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity
import com.breaktime.mustune.session_manager.impl.data.serializer.UserInfoEntitySerializer
import com.breaktime.mustune.session_manager.impl.data.source.DataSource
import com.breaktime.mustune.session_manager.impl.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val context: Context
) : DataSource {
    override suspend fun setUserInfo(
        userInfoEntity: UserInfoEntity
    ): Unit = withContext(Dispatchers.IO) {
        context.userInfoDataStore.updateData { userInfoEntity }
    }

    override suspend fun getUserInfo() = context.userInfoDataStore.data.first()

    override suspend fun removeUserInfo(): Unit = withContext(Dispatchers.IO) {
        context.userInfoDataStore.updateData { null }
    }
}

private val Context.userInfoDataStore by dataStore(Constants.PREF_NAME, UserInfoEntitySerializer)