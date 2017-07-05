package org.naver

import com.google.gson.Gson

open class Client {
    protected fun buildParams(key: String, value: String): HashMap<String, String> {
        val params = HashMap<String, String>()
        params.put(key, value)
        return params
    }

    protected fun <T> decodeJson(body: String?, modelClass: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(body, modelClass)
    }
}