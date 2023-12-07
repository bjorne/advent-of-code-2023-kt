package adventofcode

import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day06Test {

    val input = """Time:      7  15   30
Distance:  9  40  200""".trimMargin()

    @Test
    fun a() {
        assertEquals(4 * 8 * 9, Day06.a(input))
    }

    @Test
    fun b() {
        assertEquals(71503, Day06.b(input))
    }

    @Test
    fun aFull() {
        assertEquals(4568778, Day06.a(getInput(6)))
    }

    @Test
    fun bFull() {
        assertEquals(28973936, Day06.b(getInput(6)))
    }

}