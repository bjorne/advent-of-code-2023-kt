package adventofcode.shared.adventofcode

import adventofcode.Day08
import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day08Test {

    val input = """RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)""".trimMargin()

    val input2 = """LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)""".trimMargin()

    val input3 = """LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)""".trimMargin()

    @Test
    fun a() {
        assertEquals(2, Day08.a(input))
        assertEquals(6, Day08.a(input2))
    }

    @Test
    fun b() {
        assertEquals(6, Day08.b(input3))
    }

    @Test
    fun bBrute() {
        assertEquals(6, Day08.bBrute(input3))
    }

    @Test
    fun aFull() {
        assertEquals(14257, Day08.a(getInput(8)))
    }

    @Test
    fun bFull() {
        assertEquals(16187743689077, Day08.b(getInput(8)))
    }

    @Test
    fun bBruteFull() {
        // assertEquals(16187743689077, Day08.bBrute(getInput(8)))
    }
}