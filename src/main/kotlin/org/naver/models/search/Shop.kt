package org.naver.models.search

data class Shops(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Shop>
)

data class Shop(
    val title: String,
    val link: String,
    val image: String,
    val lprice: String,
    val hprice: String,
    val mallName: String,
    val productId: String,
    val productType: String
)