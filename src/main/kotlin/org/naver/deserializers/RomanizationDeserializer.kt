package org.naver.deserializers

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import org.naver.models.Romanizations
import java.lang.reflect.Type

internal class RomanizationDeserializer : JsonDeserializer<Romanizations> {
    @Throws(JsonParseException::class)
    override fun deserialize(je: JsonElement, type: Type, jdc: JsonDeserializationContext): Romanizations {
        // Get the "aResult" element from the parsed JSON
        val result = je.asJsonObject.get("aResult").asJsonArray.first()

        // Deserialize it. use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson(result, type)
    }
}