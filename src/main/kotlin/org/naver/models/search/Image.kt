package org.naver.models.search

data class Images(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Image>
)

data class Image(
    val title: String,
    val link: String,
    val thumbnail: String,
    val sizeheight: Int,
    val sizewidth: Int
)