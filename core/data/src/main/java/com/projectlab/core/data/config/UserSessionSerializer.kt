package com.projectlab.core.data.config

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.projectlab.core.domain.proto.UserSession
import java.io.InputStream
import java.io.OutputStream

object UserSessionSerializer : Serializer<UserSession> {
    override val defaultValue: UserSession = UserSession.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserSession {
        try {
            return UserSession.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserSession, output: OutputStream) = t.writeTo(output)
}
