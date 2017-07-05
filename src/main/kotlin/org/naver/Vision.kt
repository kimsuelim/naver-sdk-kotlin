package org.naver

import com.google.gson.Gson
import org.naver.models.Celebrities
import org.naver.models.Faces
import java.io.File

class Vision : Client() {
    // 유명인 얼굴인식(Beta)
    // @param image [File] 얼굴이 포함된 이미지 (최대 2MB)
    // @return [Celebrities] 인식결과
    fun celebrity(image: File): Celebrities {
        val result = Connection.post("/v1/vision/celebrity", image).body()?.string()
        val gson = Gson()
        val celebrities = gson.fromJson(result, Celebrities::class.java)
        return celebrities
    }

    // 얼굴 감지(Beta)
    // @param image [File] 얼굴이 포함된 이미지 (최대 2MB)
    // @return [Faces] 인식결과
    fun face(image: File): Faces {
        val result = Connection.post("/v1/vision/face", image).body()?.string()
        val gson = Gson()
        val faces = gson.fromJson(result, Faces::class.java)
        return faces
    }
}