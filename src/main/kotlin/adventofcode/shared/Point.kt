package adventofcode.shared

import kotlin.math.absoluteValue

data class Point(val x: Int, val y: Int) {
    val neigbors = sequence {
        yield(Point(x - 1, y - 1))
        yield(Point(x, y - 1))
        yield(Point(x + 1, y - 1))
        yield(Point(x - 1, y))
        yield(Point(x + 1, y))
        yield(Point(x - 1, y + 1))
        yield(Point(x, y + 1))
        yield(Point(x + 1, y + 1))
    }

    fun manhattanDistance(other: Point) = (x - other.x).absoluteValue + (y - other.y).absoluteValue

    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)

    operator fun rangeTo(other: Point) = sequence {
        var ix = x
        var iy = y
        val xDiffTotal = (other.x - x).absoluteValue
        val yDiffTotal = (other.y - y).absoluteValue
        yield(Point(ix, iy))
        while (ix != other.x || iy != other.y) {
            val xDiff = (other.x - ix).absoluteValue
            val yDiff = (other.y - iy).absoluteValue
            if (xDiffTotal == 0 || xDiff * yDiffTotal < yDiff * xDiffTotal) {
                iy += if (iy < other.y) 1 else -1
            } else {
                ix += if (ix < other.x) 1 else -1
            }
            yield(Point(ix, iy))
        }
    }

    // TODO: Why doesn't operator fun work?
    fun rangeUntil(other: Point) = sequence {
        val xRange = if (x < other.x) x until other.x else other.x until x
        val yRange = if (y < other.y) y until other.y else other.y until y
        for (x in xRange) {
            for (y in yRange) {
                yield(Point(x, y))
            }
        }
    }

    companion object Constants {
        val origin = Point(0, 0)
        val up = Point(0, 1)
        val down = Point(0, -1)
        val left = Point(-1, 0)
        val right = Point(1, 0)
    }
}

