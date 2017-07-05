package org.naver.models

data class Celebrities(
    val info: Info,
    val faces: List<CelebrityFace>
)

data class Info(
    val size: ImageSize,
    val faceCount: Int
)

data class ImageSize(
    val width: Int,
    val height: Int
)

data class CelebrityFace(
    val celebrity: Celebrity
)

data class Celebrity(
    val value: String,
    val confidence: Float
)