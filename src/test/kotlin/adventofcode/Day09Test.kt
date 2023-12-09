package adventofcode.shared.adventofcode

import adventofcode.Day09
import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day09Test {

    val input = """0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45""".trimMargin()
    
    @Test
    fun a() {
        assertEquals(114, Day09.a(input))
    }


    @Test
    fun b() {
        assertEquals(2, Day09.b(input))
    }

    @Test
    fun aFull() {
        assertEquals(2038472161, Day09.a(getInput(9)))
    }

    @Test
    fun bFull() {
        assertEquals(1091, Day09.b(getInput(9)))
    }

}