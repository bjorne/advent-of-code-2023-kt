package adventofcode

import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day07Test {

    val input = """32T3K 765
                  |T55J5 684
                  |KK677 28
                  |KTJJT 220
                  |QQQJA 483""".trimMargin()

    @Test
    fun a() {
        assertEquals(765 * 1 + 220 * 2 + 28 * 3 + 684 * 4 + 483 * 5, Day07.a(input))
    }

    @Test
    fun b() {
        assertEquals(5905, Day07.b(input))
    }

    @Test
    fun aFull() {
        assertEquals(250058342, Day07.a(getInput(7)))
    }

    @Test
    fun bFull() {
        assertEquals(250506580, Day07.b(getInput(7)))
    }

}