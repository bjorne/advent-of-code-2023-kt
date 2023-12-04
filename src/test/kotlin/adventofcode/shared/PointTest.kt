package adventofcode.shared

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PointTest {

    @Test
    fun plus() {
        assertEquals(Point(4, 6), Point(1, 2) + Point(3, 4))
    }

    @Test
    fun minus() {
        assertEquals(Point(-2, -2), Point(1, 2) - Point(3, 4))

    }

    @Test
    fun rangeTo() {
        assertEquals(listOf(
            Point(1, 2),
            Point(1, 3),
        ), (Point(1, 2) .. Point(1, 3)).toList())
        assertEquals(listOf(
            Point(1, 2),
            Point(2, 2),
            Point(2, 3),
            ), (Point(1, 2) .. Point(2, 3)).toList())
        assertEquals(listOf(
            Point(1, 2),
            Point(2, 2),
            Point(2, 3),
            Point(3, 3),
            Point(4, 3),
        ), (Point(1, 2) .. Point(4, 3)).toList())

    }

    @Test
    fun rangeUntil() {
    }
}