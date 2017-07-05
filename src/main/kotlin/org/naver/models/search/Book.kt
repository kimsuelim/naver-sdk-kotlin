package org.naver.models.search

data class Books(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Book>
)

data class Book(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val price: String,
    val discount: String,
    val publisher: String,
    val pubdate: String,
    val isbn: String,
    val description: String
)
