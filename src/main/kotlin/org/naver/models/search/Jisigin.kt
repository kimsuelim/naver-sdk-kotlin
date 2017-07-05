package org.naver.models.search

data class Jisigins(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Jisigin>
)

data class Jisigin(
    val title: String,
    val link: String,
    val description: String
)