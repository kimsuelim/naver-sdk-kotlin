package org.naver.models.search

data class Docs(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Doc>
)

data class Doc(
    val title: String,
    val link: String,
    val description: String
)