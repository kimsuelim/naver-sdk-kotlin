import org.naver.Naver

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import okio.Okio
import java.io.File

class VoiceTest {
    private val naver: Naver = Naver(clientId = "GssyV0tCqRYPMRkDKsgG", clientSecret = "ScVEl_cDJA")

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun tts() {
        val results = naver.voice().tts(speaker = "mijin", speed = 0, text = "기술은 사람을 대체하는 것이 아니라 기술의 도움으로 사람을 더 창조적으로 만들 것이라 믿는다.")
        val downloadedFile = File("src/test/resources/tts.mp3")
        val sink = Okio.buffer(Okio.sink(downloadedFile))
        sink.writeAll(results)
        sink.close()
        assertNotNull(results)
    }
}