import org.naver.Naver

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MapTest {
    private val naver: Naver = Naver(clientId = "GssyV0tCqRYPMRkDKsgG", clientSecret = "ScVEl_cDJA")

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun geocode() {
        val results = naver.map().geocode(query = "불정로 6")
        assertEquals(results.total, 1)
        assertEquals(results.userquery, "불정로 6")
        assertEquals(results.items.size, 1)
        assertNotNull(results.items.first().addrdetail)
        assertTrue(results.items.first().isRoadAddress)
        assertFalse(results.items.first().isAdmAddress)
        assertNotNull(results.items.first().point)
    }

    @Test
    fun reverseGeocode() {
        val results = naver.map().reverseGeocode(lat = "127.1141382", lng = "37.3599968")
        assertEquals(results.total, 2)
        assertEquals(results.userquery, "127.1141382,37.3599968")
        assertEquals(results.items.size, 2)
        assertNotNull(results.items.first().addrdetail)
        assertFalse(results.items.first().isRoadAddress)
        assertFalse(results.items.first().isAdmAddress)
        assertNotNull(results.items.first().point)
    }
}
