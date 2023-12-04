package adventofcode.day04

import adventofcode.shared.getInput
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day04Test {

    val input = """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""".trimMargin()
    @Test
    fun day04a() {
        assertEquals(13, day04a(input))
    }

    @Test
    fun day04b() {
        assertEquals(30, day04b(input))
    }

    @Test
    fun day04b2() {
        assertEquals(30, day04b2(input))
    }


    @Test
    fun day04aFull() {
        assertEquals(23847, day04a(getInput(4)))
    }

    @Test
    fun day04bFull() {
        assertEquals(8570000, day04b(getInput(4)))
    }

}