package org.naver

import org.naver.models.Romanizations
import org.naver.models.Translation
import com.google.gson.GsonBuilder
import org.naver.deserializers.RomanizationDeserializer
import org.naver.deserializers.TranslationDeserializer

class Papago : Client() {
    fun romanization(query: String): Romanizations {
        val result = Connection.get("get", "/v1/krdict/romanization", query).body()?.string()
        val gson = GsonBuilder()
                .registerTypeAdapter(Romanizations::class.java, RomanizationDeserializer())
                .create()
        val romanizations = gson.fromJson(result, Romanizations::class.java)
        return romanizations
    }

    fun translate(source: String, target: String, text: String): Translation {
        var params = buildParams(key = "source", value = source)
        params.put("target", target)
        params.put("text", text)

        val result = Connection.post("post", "/v1/language/translate", params).body()?.string()
        val gson = GsonBuilder()
                .registerTypeAdapter(Translation::class.java, TranslationDeserializer())
                .create()
        val translation = gson.fromJson(result, Translation::class.java)
        return translation
    }
}