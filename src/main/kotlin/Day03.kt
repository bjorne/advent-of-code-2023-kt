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

data class Number(val position: Point, val value: Int) {
    val extent = sequence {
        val len = value.toString().length
        for (i in 0 until len) {
            yield(Point(position.x + i, position.y))
        }
    }
}

data class Symbol(val position: Point, val value: Char)

val numberRegex03 = """(\d+)""".toRegex()
val symbolRegex = """([^\d\.])""".toRegex()

private fun parseInput(input: String): Pair<List<Number>, List<Symbol>> {
    val schematic = input.split("\n")
    val width = schematic[0].length
    val height = schematic.size
    val numbers = schematic.flatMapIndexed { index, line ->
        numberRegex03.findAll(line).map {
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

fun day03a(input: String): Int {
    val (numbers, symbols) = parseInput(input)
    return numbers.filter { number ->
        symbols.any { symbol ->
            number.extent.flatMap { it.neigbors }.any { it == symbol.position }
        }
    }.map { it.value }.sum()
}


fun day03b(input: String): Int {
    val (numbers, symbols) = parseInput(input)
    return symbols.filter { it.value == '*' }.map { symbol ->
        val adjacent = numbers.filter { number ->
            number.extent.flatMap { it.neigbors }.any { it == symbol.position }
        }
        val gearRatio = adjacent.map { it.value }.reduce(Int::times)
        if (adjacent.size == 2) gearRatio else 0
    }.sum()
}