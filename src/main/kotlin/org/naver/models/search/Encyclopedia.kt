package org.naver.models.search

data class Encyclopedias(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Encyclopedia>
)

data class Encyclopedia(
    val title: String,
    val link: String,
    val description: String,
    val thumbnail: String
)

