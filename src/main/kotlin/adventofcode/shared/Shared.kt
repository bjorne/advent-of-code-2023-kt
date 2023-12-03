package adventofcode.shared

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
}
