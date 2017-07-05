package org.naver.deserializers

import org.naver.models.Translation

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type

internal class TranslationDeserializer : JsonDeserializer<Translation> {
    @Throws(JsonParseException::class)
    override fun deserialize(je: JsonElement, type: Type, jdc: JsonDeserializationContext): Translation {
        // Get the "message.result" element from the parsed JSON
        val result = je.asJsonObject.get("message").asJsonObject.get("result")

        // Deserialize it. use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson(result, type)
    }
}