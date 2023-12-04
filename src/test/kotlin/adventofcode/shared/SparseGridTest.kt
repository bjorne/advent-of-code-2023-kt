package adventofcode.shared

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SparseGridTest {
    @Test
    fun drawWithoutTicks() {
        SparseGrid(
            mapOf(
                Point(0, 0) to 1,
                Point(1, 1) to 4,
            )
        ).draw(ticks = false).also {
            assertEquals(". 4 \n1 . ", it)
        }
    }

    @Test
    fun drawWithTicks() {
        SparseGrid(mapOf(
            Point(0, 0) to "a",
            Point(4, 1) to "b",
            Point(2, 20) to "c",
            Point(3, 3) to "d",
            Point(15, 15) to "e",
        )).draw().also {
            val expected = """   20 . . c . . . . . . . . . . . . . 
                             |   19 . . . . . . . . . . . . . . . . 
                             |   18 . . . . . . . . . . . . . . . . 
                             |   17 . . . . . . . . . . . . . . . . 
                             |   16 . . . . . . . . . . . . . . . . 
                             |   15 . . . . . . . . . . . . . . . e 
                             |   14 . . . . . . . . . . . . . . . . 
                             |   13 . . . . . . . . . . . . . . . . 
                             |   12 . . . . . . . . . . . . . . . . 
                             |   11 . . . . . . . . . . . . . . . . 
                             |   10 . . . . . . . . . . . . . . . . 
                             |    9 . . . . . . . . . . . . . . . . 
                             |    8 . . . . . . . . . . . . . . . . 
                             |    7 . . . . . . . . . . . . . . . . 
                             |    6 . . . . . . . . . . . . . . . . 
                             |    5 . . . . . . . . . . . . . . . . 
                             |    4 . . . . . . . . . . . . . . . . 
                             |    3 . . . d . . . . . . . . . . . . 
                             |    2 . . . . . . . . . . . . . . . . 
                             |    1 . . . . b . . . . . . . . . . . 
                             |    0 a . . . . . . . . . . . . . . . 
                             |      |         |         |         |
                             |      0         5        10        15""".trimMargin() // .also { println(it)}
            assertEquals(expected, it)
        }

//        SparseGrid((Point(0, 0) .. Point(4, 14)).map { it to "*" }.toMap() + mapOf(
//            Point(0, 0) to "a",
//            Point(4, 14) to "b"
//        )).apply { println(draw()) }
//
//        SparseGrid((Point(0, 0) .. Point(14, 14)).map { it to "*" }.toMap() + mapOf(
//            Point(0, 0) to "a",
//            Point(14, 14) to "b"
//        )).apply { println(draw()) }
//
//        SparseGrid((Point(-14, 14) .. Point(0, 0)).map { it to "*" }.toMap() + mapOf(
//            Point(-14,   14) to "a",
//            Point(0, 0) to "b"
//        )).apply { println(draw()) }
//
//        SparseGrid((Point(14, -14) .. Point(0, 0)).map { it to "*" }.toMap() + mapOf(
//            Point(14,   -14) to "a",
//            Point(0, 0) to "b"
//        )).apply { println(draw()) }

    }
}