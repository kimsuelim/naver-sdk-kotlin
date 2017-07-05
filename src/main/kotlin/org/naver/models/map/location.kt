package org.naver.models.map

data class Locations(
    val total: Int,
    val userquery: String,
    val items: List<Location>
)

data class Location(
    val address: String,
    val addrdetail: AddressDetail,
    val isRoadAddress: Boolean,
    val isAdmAddress: Boolean,
    val point: Point
)

data class AddressDetail(
    val country: String,
    val sido: String,
    val sigugun: String,
    val dongmyun: String,
    val rest: String
)

data class Point(
    val x: Double,
    val y: Double
)