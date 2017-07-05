import org.naver.Naver

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class SearchTest {
    private val naver: Naver = Naver(clientId = "GssyV0tCqRYPMRkDKsgG", clientSecret = "ScVEl_cDJA")

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun blog() {
        val results = naver.search().blog(query = "블로그")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun news() {
        val results = naver.search().news(query = "뉴스")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun book() {
        val results = naver.search().book(query = "책")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun adult() {
        val adult_results = naver.search().adult(query = "성인")
        assertTrue(adult_results.isAdult())

        val not_adult_results = naver.search().adult(query = "청소년")
        assertFalse(not_adult_results.isAdult())
    }

    @Test
    fun encyclopedia() {
        val results = naver.search().encyclopedia(query = "백과 사전")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun movie() {
        val results = naver.search().movie(query = "킬빌")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 3)
        assertEquals(results.items.size, 3)
    }

    @Test
    fun cafearticle() {
        val results = naver.search().cafearticle(query = "카페글")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun jisigin() {
        val results = naver.search().jisigin(query = "지식인")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun place() {
        val results = naver.search().place(query = "제주도")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun errata() {
        var results = naver.search().errata(query = "오타")
        assertFalse(results.isErrata())
        assertEquals(results.errata, "")

        results = naver.search().errata(query = "dhxk")
        assertTrue(results.isErrata())
        assertEquals(results.errata, "오타")
    }

    @Test
    fun webkr() {
        val results = naver.search().webkr(query = "웹문서")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun image() {
        val results = naver.search().image(query = "이미지")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun shop() {
        val results = naver.search().shop(query = "쇼핑")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }

    @Test
    fun doc() {
        val results = naver.search().doc(query = "전문자료")
        assertNotNull(results.lastBuildDate)
        assertNotNull(results.total)
        assertEquals(results.start, 1)
        assertEquals(results.display, 10)
        assertEquals(results.items.size, 10)
    }
}