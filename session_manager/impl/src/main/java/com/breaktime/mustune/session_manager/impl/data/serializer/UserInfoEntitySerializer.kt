package com.breaktime.mustune.session_manager.impl.data.serializer

import androidx.datastore.core.Serializer
import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserInfoEntitySerializer : Serializer<UserInfoEntity?> {
    override val defaultValue: UserInfoEntity?
        get() = null

    override suspend fun readFrom(input: InputStream): UserInfoEntity? {
        return try {
            Json.decodeFromString(
                deserializer = UserInfoEntity.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    override suspend fun writeTo(userInfo: UserInfoEntity?, output: OutputStream) =
        withContext(Dispatchers.IO) {
            output.write(
                userInfo?.let {
                    Json.encodeToString(
                        serializer = UserInfoEntity.serializer(),
                        value = userInfo
                    ).encodeToByteArray()
                } ?: byteArrayOf()
            )
        }
}