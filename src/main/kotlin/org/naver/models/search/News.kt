package org.naver.models.search

import java.util.Date

data class News(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<NewsItem>
)

data class NewsItem(
    val title: String,
    val originallink: String,
    val link: String,
    val description: String,
    val pubDate: Date // Fri, 07 Jul 2017 18:03:49 +0900
)