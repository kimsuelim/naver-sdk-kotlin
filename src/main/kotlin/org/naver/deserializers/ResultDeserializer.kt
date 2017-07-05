package org.naver.deserializers

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type

internal class ResultDeserializer<T> : JsonDeserializer<T> {
    @Throws(JsonParseException::class)
    override fun deserialize(je: JsonElement, type: Type, jdc: JsonDeserializationContext): T {
        // Get the "result" element from the parsed JSON
        val result = je.asJsonObject.get("result")

        // Deserialize it. use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson(result, type)
    }
}