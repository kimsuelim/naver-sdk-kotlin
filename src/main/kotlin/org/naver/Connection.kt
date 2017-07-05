package org.naver

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.HttpUrl
import okhttp3.FormBody
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.io.File

object Connection {
    var clientId: String? = Configuration.clientId
    var clientSecret: String? = Configuration.clientSecret
    val apiBaseUrl: String = Configuration.defaultApiBaseUri
    val userAgent: String = Configuration.defaultUserAgent
    var client: OkHttpClient

    init {
        if (Configuration.debug) {
            val logging = HttpLoggingInterceptor()
            logging.level = Level.BODY
            this.client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
        } else {
            this.client = OkHttpClient()
        }
    }

    fun get(verb: String, path: String, params: String): Response {
        val httpBuider = HttpUrl.parse("${apiBaseUrl}${path}")!!.newBuilder()
        val url = httpBuider.addQueryParameter("query", params)
                .build()

        val request = Request.Builder()
                .url(url)
                .header("User-Agent", userAgent)
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .build()

        val response = request(request)
        return response
    }

    fun post(verb: String, path: String, params: HashMap<String, String>): Response {
        val JSON = MediaType.parse("application/json; charset=utf-8")

        // Initialize Builder (not RequestBody)
        val builder = FormBody.Builder()

        // Add Params to Builder
        params.forEach { (key, value) -> builder.add(key, value) }

        // Create RequestBody
        val requestBody = builder.build()

        val request = Request.Builder()
                .url("${apiBaseUrl}${path}")
                .header("User-Agent", userAgent)
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .post(requestBody)
                .build()

        val response = request(request)
        return response
    }

    fun post(path: String, file: File): Response {
        // Create RequestBody
        val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.name,
                        RequestBody.create(MediaType.parse("image/jpeg"), file))
                .build()

        val request = Request.Builder()
                .url("${apiBaseUrl}${path}")
                .header("User-Agent", userAgent)
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .post(requestBody)
                .build()

        val response = request(request)
        return response
    }

    private fun request(request: Request): Response {
        val response = client.newCall(request).execute()
        if (!response.isSuccessful()) {
            // throw APIException(response.message())
            throw APIException(response.body()?.string())
        } else {
            return response
        }
    }
}