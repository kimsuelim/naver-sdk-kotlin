package org.naver.models.search

data class Webkrs(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Webkr>
)

data class Webkr(
    val title: String,
    val link: String,
    val description: String
)