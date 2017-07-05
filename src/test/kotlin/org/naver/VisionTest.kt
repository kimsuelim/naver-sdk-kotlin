import org.naver.Naver

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.io.File

class VisionTest {
    private val naver: Naver = Naver(clientId = "GssyV0tCqRYPMRkDKsgG", clientSecret = "ScVEl_cDJA")

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun celebrity() {
        val file = File("src/test/resources/park.png")
        val result = naver.vision().celebrity(image = file)
        assertNotNull(result.info.size)
        assertEquals(result.info.faceCount, 1)
        assertEquals(result.faces.first().celebrity.value, "박성웅")
        assertNotNull(result.faces.first().celebrity.confidence)
    }

    @Test
    fun face() {
        val file = File("src/test/resources/park.png")
        val result = naver.vision().face(image = file)
        assertNotNull(result.info.size)
        assertEquals(result.faces.first().gender.value, "male")
        assertNotNull(result.faces.first().age.value)
        assertEquals(result.faces.first().emotion.value, "smile")
        assertEquals(result.faces.first().pose.value, "frontal_face")
    }
}