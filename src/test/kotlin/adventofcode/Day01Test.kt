package adventofcode

import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01Test {

    val input = """1abc2
                |pqr3stu8vwx
                |a1b2c3d4e5f
                |treb7uchet""".trimMargin()

    @Test
    fun a() {
        assertEquals(142, Day01.a(input))
    }

    val input2 = """two1nine
                    |eightwothree
                    |abcone2threexyz
                    |xtwone3four
                    |4nineeightseven2
                    |zoneight234
                    |7pqrstsixteen""".trimMargin()

    @Test
    fun b() {
        assertEquals(281, Day01.b(input2))
    }

    @Test
    fun aFull() {
        assertEquals(55386, Day01.a(getInput(1)))
    }

    @Test
    fun bFull() {
        assertEquals(54824, Day01.b(getInput(1)))
    }

}