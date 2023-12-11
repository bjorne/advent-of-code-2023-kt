package adventofcode

import adventofcode.shared.Point
import adventofcode.shared.SparseGrid

object Day11 {
    private fun solve(input: String, expansionFactor: Int): Long {
        val grid = input.lines().flatMapIndexed { index, line ->
            line.mapIndexed { col, c -> Point(col, input.lines().size - index) to c }
        }.let { SparseGrid(it.toMap()) }

        // println(grid.draw())

        val expandingRows = grid.yRange.filter { y ->
            grid.xRange.all { x -> grid[Point(x, y)] == '.' }
        }
        val expandingCols = grid.xRange.filter { x ->
            grid.yRange.all { y -> grid[Point(x, y)] == '.' }
        }
        val expanded = grid.points.filter { it.value == '#' }.map { pp ->
            val p = pp.key
            Point(
                p.x + (expansionFactor - 1) * expandingCols.count { it < p.x },
                p.y + (expansionFactor - 1) * expandingRows.count { it < p.y })
        }

        // println(expanded.map { it to '#' }.toMap().let { SparseGrid(it).draw() })

        val pairs: MutableMap<Set<Point>, Long> = mutableMapOf()
        for (i in expanded.indices) {
            for (j in 0 until i) {
                val k = setOf(expanded[i], expanded[j])
                if (k in pairs) {
                    continue
                }
                pairs[k] = expanded[i].manhattanDistance(expanded[j])
            }
        }
        return pairs.values.sum()
    }

    fun a(input: String): Int = solve(input, 2).toInt()

    fun b(input: String, expansionFactor: Int = 1000000): Long = solve(input, expansionFactor)
}