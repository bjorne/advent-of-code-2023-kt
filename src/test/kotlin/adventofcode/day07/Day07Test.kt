package adventofcode.shared.adventofcode.day07

import adventofcode.day07.Day07
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
    fun day07a() {
        assertEquals(765 * 1 + 220 * 2 + 28 * 3 + 684 * 4 + 483 * 5, Day07.a(input))
    }

    @Test
    fun day07b() {
        assertEquals(5905, Day07.b(input))
    }

    @Test
    fun day07aFull() {
        assertEquals(250058342, Day07.a(getInput(7)))
    }

    @Test
    fun day07bFull() {
        assertEquals(250506580, Day07.b(getInput(7)))
    }

}