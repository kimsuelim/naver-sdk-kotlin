package org.naver.models

data class Faces(
    val info: Info,
    val faces: List<Face>
)

data class Face(
    val roi: Roi,
    val landmark: Landmark,
    val gender: Gender,
    val age: Age,
    val emotion: Emotion,
    val pose: Pose
)

data class Roi(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int
)

data class Landmark(
    val leftEye: LeftEye,
    val rightEye: RightEye,
    val nose: Nose,
    val leftMouth: LeftMouth,
    val rightMouth: RightMouth
)

data class LeftEye(
    val x: Int,
    val y: Int
)

data class RightEye(
    val x: Int,
    val y: Int
)

data class Nose(
    val x: Int,
    val y: Int
)

data class LeftMouth(
    val x: Int,
    val y: Int
)

data class RightMouth(
    val x: Int,
    val y: Int
)

data class Gender(
    val value: String,
    val confidence: Double
)

data class Age(
    val value: String,
    val confidence: Double
)

data class Emotion(
    val value: String,
    val confidence: Double
)

data class Pose(
    val value: String,
    val confidence: Double
)