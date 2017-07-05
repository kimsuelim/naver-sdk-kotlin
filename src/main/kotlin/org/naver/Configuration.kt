package org.naver

object Configuration {
    val defaultUserAgent: String = "NAVER Kotlin SDK"
    val defaultApiBaseUri: String = "https://openapi.naver.com"
    val defaultOauthBaseUri: String = "https://nid.naver.com"

    var clientId: String? = null
    var clientSecret: String? = null
    var debug: Boolean = false

    fun configure(clientId: String, clientSecret: String, debug: Boolean = false) {
        Configuration.clientId = clientId
        Configuration.clientSecret = clientSecret
        Configuration.debug = debug
    }
}
