package com.projectlab.core.data.config

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.projectlab.core.data.proto.AmadeusToken
import java.io.InputStream
import java.io.OutputStream

object AmadeusTokenSerializer : Serializer<AmadeusToken> {
    override val defaultValue: AmadeusToken = AmadeusToken.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AmadeusToken {
        try {
            return AmadeusToken.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: AmadeusToken, output: OutputStream) = t.writeTo(output)
}
