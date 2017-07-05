package org.naver

import org.naver.deserializers.ResultDeserializer
import org.naver.models.ShortenUrl
import com.google.gson.GsonBuilder

class ShortUrl : Client() {
    fun shorten(url: String): ShortenUrl {
        val params = buildParams(key = "url", value = url)
        val result = Connection.post("post", "/v1/util/shorturl", params).body()?.string()
        val shortenUrl = decode(result)
        return shortenUrl
    }

    private fun decode(result: String?): ShortenUrl {
        val gson = GsonBuilder()
                .registerTypeAdapter(ShortenUrl::class.java, ResultDeserializer<ShortUrl>())
                .create()
        val shortenUrl = gson.fromJson(result, ShortenUrl::class.java)
        return shortenUrl
    }
}