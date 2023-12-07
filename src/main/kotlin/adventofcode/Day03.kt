package adventofcode

import adventofcode.shared.Point

object Day03 {
    data class Number(val position: Point, val value: Int) {
        val extent = sequence {
            val len = value.toString().length
            for (i in 0 until len) {
                yield(Point(position.x + i, position.y))
            }
        }
    }

    data class Symbol(val position: Point, val value: Char)

    val numberRegex = """(\d+)""".toRegex()
    val symbolRegex = """([^\d\.])""".toRegex()

    private fun parseInput(input: String): Pair<List<Number>, List<Symbol>> {
        val schematic = input.split("\n")
        val numbers = schematic.flatMapIndexed { index, line ->
            numberRegex.findAll(line).map {
                Number(Point(it.range.first, index), it.value.toInt())
            }
        }
        val symbols = schematic.flatMapIndexed { index, line ->
            symbolRegex.findAll(line).map {
                Symbol(Point(it.range.first, index), it.value.first().toChar())
            }
        }
        return Pair(numbers, symbols)
    }

    fun a(input: String): Int {
        val (numbers, symbols) = parseInput(input)
        return numbers.filter { number ->
            symbols.any { symbol ->
                number.extent.flatMap { it.neigbors }.any { it == symbol.position }
            }
        }.map { it.value }.sum()
    }


    fun b(input: String): Int {
        val (numbers, symbols) = parseInput(input)
        return symbols.filter { it.value == '*' }.map { symbol ->
            val adjacent = numbers.filter { number ->
                number.extent.flatMap { it.neigbors }.any { it == symbol.position }
            }
            val gearRatio = adjacent.map { it.value }.reduce(Int::times)
            if (adjacent.size == 2) gearRatio else 0
        }.sum()
    }
}