package adventofcode.shared.adventofcode

import adventofcode.Day11
import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11Test {

    val input = """...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#....."""

    @Test
    fun a() {
        assertEquals(374, Day11.a(input))
    }

    @Test
    fun b() {
        assertEquals(1030, Day11.b(input, 10))
    }

    @Test
    fun b2() {
        assertEquals(8410, Day11.b(input, 100))
    }

    @Test
    fun aFull() {
        assertEquals(9445168, Day11.a(getInput(11)))
    }

    @Test
    fun bFull() {
        assertEquals(742305960572, Day11.b(getInput(11)))
    }

}