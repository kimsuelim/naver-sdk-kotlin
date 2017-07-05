package org.naver.models.search

data class Blogs(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Blog>
)

data class Blog(
    val title: String,
    val link: String,
    val description: String,
    val bloggername: String,
    val bloggerlink: String,
    val postdate: String
)