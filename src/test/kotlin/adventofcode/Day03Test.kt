package adventofcode

import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
    fun a() {
        assertEquals(4361, Day03.a(input))
    }

    @Test
    fun b() {
        assertEquals(16345 + 451490, Day03.b(input))
    }

    @Test
    fun aFull() {
        assertEquals(556057, Day03.a(getInput(3)))
    }

    @Test
    fun bFull() {
        assertEquals(82824352, Day03.b(getInput(3)))
    }

}