package org.naver.models

data class ShortenUrl(
    val hash: String,
    val url: String,
    val orgUrl: String
)