package org.naver.models.search

data class Places(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Place>
)

data class Place(
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: String,
    val mapy: String
)