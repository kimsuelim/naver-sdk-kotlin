import org.naver.Naver

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ShorturlTest {
    private val naver: Naver = Naver(clientId = "GssyV0tCqRYPMRkDKsgG", clientSecret = "ScVEl_cDJA")

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shorten() {
        val results = naver.shortUrl().shorten(url = "https://github.com/kimsuelim")
        assertNotNull(results.hash)
        assertNotNull(results.url)
        assertEquals(results.orgUrl, "https://github.com/kimsuelim")
    }
}