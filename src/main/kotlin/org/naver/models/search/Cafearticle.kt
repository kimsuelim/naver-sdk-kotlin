package org.naver.models.search

data class Cafearticles(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Cafearticle>
)

data class Cafearticle(
    val title: String,
    val link: String,
    val description: String,
    val cafename: String,
    val cafeurl: String
)