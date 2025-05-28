package com.projectlab.core.data.config

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.projectlab.core.domain.proto.SearchHistory
import java.io.InputStream
import java.io.OutputStream

object SearchHistorySerializer : Serializer<SearchHistory> {
    override val defaultValue: SearchHistory = SearchHistory.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SearchHistory {
        try {
            return SearchHistory.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SearchHistory, output: OutputStream) = t.writeTo(output)
}
