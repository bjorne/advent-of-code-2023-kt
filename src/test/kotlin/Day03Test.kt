import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day03Test {

    val input = """467..114..
                  |...*......
                  |..35..633.
                  |......#...
                  |617*......
                  |.....+.58.
                  |..592.....
                  |......755.
                  |...${'$'}.*....
                  |.664.598..""".trimMargin()
    @Test
    fun day03a() {
        assertEquals(4361, day03a(input))
    }

    @Test
    fun day03b() {
        assertEquals(16345 + 451490, day03b(input))
    }

    @Test
    fun day03aFull() {
        assertEquals(556057, day03a(getInput(3)))
    }

    @Test
    fun day03bFull() {
        assertEquals(82824352, day03b(getInput(3)))
    }

}