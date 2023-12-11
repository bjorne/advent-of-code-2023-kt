package adventofcode

import adventofcode.shared.Point
import adventofcode.shared.SparseGrid

object Day10 {
    private val connects = mapOf(
        '|' to setOf(Point.up, Point.down),
        '-' to setOf(Point.left, Point.right),
        'L' to setOf(Point.right, Point.up),
        'J' to setOf(Point.left, Point.up),
        '7' to setOf(Point.left, Point.down),
        'F' to setOf(Point.right, Point.down),
    )
    private val corners = setOf('7', 'F', 'L', 'J')

    private fun connects(pair: Pair<Point, Char>): Set<Point> = pair.let { (p, c) ->
        connects[c]?.map { p + it }?.toSet() ?: setOf()
    }

    private fun parseInput(input: String) =
        input.lines().flatMapIndexed { row, line ->
            line.mapIndexedNotNull { col, c ->
                when (c) {
                    '.' -> null
                    else -> Point(col, input.lines().size - row) to c
                }
            }
        }.let { SparseGrid(it.toMap()) }

    private fun walk(grid: SparseGrid<Char>): List<Point> {
        val start = grid.points.filter { it.value == 'S' }.keys.first()
        var current = start
        val visited = mutableListOf<Point>()
        var prev: Point? = null
        var i = 0
        do {
            i++
            val c = grid[current]
            val next = when (c) {
                'S' -> current.neighbors.find { neighbor ->
                    grid.points.containsKey(neighbor) && connects(neighbor to grid[neighbor]!!).contains(current)
                }!!

                else -> (connects(current to c!!)!! - prev!!).first()
            }
            prev = current
            visited.add(current)
            current = next
        } while (current != start)
        return visited
    }

    fun a(input: String): Int {
        val grid = parseInput(input)
        return walk(grid).size / 2
    }

    private fun isCandidate(grid: SparseGrid<Char>, p: Point): Boolean {
        val c = grid[p]
        return c != null && grid.bounds(p)
    }

    private fun isLeakagePoint(grid: SparseGrid<Char>, o: Point, p: Point): Boolean {
        val c = grid[p]
        return c in corners && p.neighbors.any { n ->
            n in o.neighbors && grid.points.containsKey(n) && grid[n] in corners && !connects(n to grid[n]!!).contains(
                p
            )
        }
    }

    data class Area(val points: Set<Point>, val isValid: Boolean, val hasLeak: Boolean)

    private fun explore(grid: SparseGrid<Char>, p: Point, explored: Set<Point>): Area {
        if (grid[p] != null || !grid.bounds(p)) return Area(setOf(), false, false)
        val area = mutableSetOf<Point>()
        val stack = mutableListOf(p)
        var isValid = true
        var hasLeak = false
        while (stack.isNotEmpty()) {
            //if (stack.size > 100) break
            //println("Stack size: ${stack.size} -- ${stack.last()} ${grid.bounds(stack.last())}")
            //println(SparseGrid(grid.points + area.map { it to '@' }.toMap()).draw())
            val current = stack.removeLast()
            if (current.x == grid.xRange.first || current.x == grid.xRange.last || current.y == grid.yRange.first || current.y == grid.yRange.last) {
                isValid = false
            }
            if (current in explored || current in area) continue
            area.add(current)
            if (current.neighbors.any { isLeakagePoint(grid, current, it) }) hasLeak = true
            stack.addAll(current.neighbors.filter { grid.bounds(it) && grid[it] == null && it !in area && it !in explored })
        }
        // println(SparseGrid(grid.points + area.map { it to '@' }.toMap()).draw())
        return Area(area, isValid, hasLeak)
    }

    fun b(input: String): Int {
        val grid = parseInput(input)
        val visited = walk(grid)
        val explored = mutableSetOf<Point>()
        val areas = mutableListOf<Area>()
        val visitedGrid = SparseGrid(visited.map { it to grid[it]!! }.toMap())
        for (p in visited.filter { visitedGrid[it] in corners }) {
            val c = grid[p]
            val candidate = when (c) {
                '7' -> Point(p.x - 1, p.y - 1)
                'F' -> Point(p.x + 1, p.y - 1)
                'L' -> Point(p.x + 1, p.y + 1)
                'J' -> Point(p.x - 1, p.y + 1)
                else -> throw Exception("Invalid corner")
            }
            val area = explore(visitedGrid, candidate, explored)
            if (area.points.isNotEmpty()) {
                if (area.isValid) {
                    println("Found area (leak=${area.hasLeak}) of size ${area.points.size} at $candidate")
                    areas.add(area)
                }
                explored.addAll(area.points)
            }
        }
        println(SparseGrid(visitedGrid.points + areas.flatMap { area -> area.points.map { it to if (area.hasLeak) '#' else '@' } }
            .toMap()).draw())
        return areas.filter { !it.hasLeak }.sumOf { it.points.size }
    }
}