package adventofcode.shared.adventofcode

import adventofcode.Day10
import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {

    val input = """.....
.S-7.
.|.|.
.L-J.
....."""

    @Test
    fun a() {
        assertEquals(4, Day10.a(input))
    }

    val input2 = """..F7.
.FJ|.
SJ.L7
|F--J
LJ..."""

    val input3 = """7-F7-
.FJ|7
SJLL7
|F--J
LJ.LJ"""

    @Test
    fun a2() {
        assertEquals(8, Day10.a(input2))
    }

    @Test
    fun a3() {
        assertEquals(8, Day10.a(input3))
    }

    val input4 = """...........
.S-------7.
.|F-----7|.
.||.....||.
.||.....||.
.|L-7.F-J|.
.|..|.|..|.
.L--J.L--J.
..........."""

    @Test
    fun b() {
        assertEquals(4, Day10.b(input4))
    }

    val input5 = """..........
.S------7.
.|F----7|.
.||....||.
.||....||.
.|L-7F-J|.
.|..||..|.
.L--JL--J.
.........."""

    @Test
    fun b2() {
        assertEquals(4, Day10.b(input5))
    }

    @Test
    fun aFull() {
        assertEquals(6864, Day10.a(getInput(10)))
    }

//    @Test
//    fun bFull() {
//        assertEquals(1, Day10.b(getInput(10)))
//    }

}