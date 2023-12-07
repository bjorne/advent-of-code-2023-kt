package adventofcode

import adventofcode.shared.getInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test {

    val input = """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4""".trimMargin()

    @Test
    fun a() {
        assertEquals(35, Day05.a(input))
    }

    @Test
    fun b() {
        assertEquals(46, Day05.b(input))
    }

    @Test
    fun aFull() {
        assertEquals(324724204, Day05.a(getInput(5)))
    }

    @Test
    fun bFull() {
        assertEquals(104070862, Day05.b(getInput(5)))
    }

}