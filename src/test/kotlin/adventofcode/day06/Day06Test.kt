package adventofcode.day06

import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day06Test {

    val input = """Time:      7  15   30
Distance:  9  40  200""".trimMargin()

    @Test
    fun day06a() {
        assertEquals(4 * 8 * 9, day06a(input))
    }

    @Test
    fun day06b() {
        assertEquals(71503, day06b(input))
    }

    @Test
    fun day06aFull() {
        assertEquals(4568778, day06a(getInput(6)))
    }

    @Test
    fun day06bFull() {
        assertEquals(28973936, day06b(getInput(6)))
    }

}