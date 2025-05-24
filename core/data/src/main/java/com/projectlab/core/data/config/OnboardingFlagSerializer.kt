package com.projectlab.core.data.config

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.projectlab.core.domain.proto.OnboardingFlag
import java.io.InputStream
import java.io.OutputStream

object OnboardingFlagSerializer : Serializer<OnboardingFlag> {
    override val defaultValue: OnboardingFlag = OnboardingFlag.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): OnboardingFlag {
        try {
            return OnboardingFlag.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: OnboardingFlag, output: OutputStream) = t.writeTo(output)
}
