import org.naver.Naver

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class PapagoTest {
    private val naver: Naver = Naver(clientId = "GssyV0tCqRYPMRkDKsgG", clientSecret = "ScVEl_cDJA")

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun romanization() {
        val results = naver.papago().romanization(query = "김수림")
        assertEquals(results.firstName, "김")
        assertEquals(results.items.first().name, "Kim Soorim")
        assertEquals(results.items.first().score, 99)
    }

    @Test
    fun translate() {
        val result = naver.papago().translate(source = "ko", target = "en", text = "기술은 사람을 대체하는 것이 아니라 기술의 도움으로 사람을 더 창조적으로 만들 것이라 믿는다.")
        assertNotNull(result.translatedText)
        assertEquals(result.srcLangType, "ko")
    }
}
