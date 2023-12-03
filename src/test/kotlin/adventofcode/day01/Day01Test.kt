package adventofcode.day01

import adventofcode.shared.getInput
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day01Test {

    val input = """1abc2
                |pqr3stu8vwx
                |a1b2c3d4e5f
                |treb7uchet""".trimMargin()
    @Test
    fun day01a() {
        assertEquals(142, day01a(input))
    }

    val input2 = """two1nine
                    |eightwothree
                    |abcone2threexyz
                    |xtwone3four
                    |4nineeightseven2
                    |zoneight234
                    |7pqrstsixteen""".trimMargin()
    @Test
    fun day01b() {
        assertEquals(281, day01b(input2))
    }

    @Test
    fun day01aFull() {
        assertEquals(55386, day01a(getInput(1)))
    }

    @Test
    fun day01bFull() {
        assertEquals(54824, day01b(getInput(1)))
    }

}