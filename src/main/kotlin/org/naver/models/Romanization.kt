package org.naver.models

import com.google.gson.annotations.SerializedName

data class Romanizations(
    @SerializedName("sFirstName")
    val firstName: String,
    @SerializedName("aItems")
    val items: List<Romanization>
)

data class Romanization(
    val name: String,
    val score: Int
)