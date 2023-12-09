package adventofcode

object Day09 {
    private fun layers(line: String): MutableList<List<Int>> {
        val numbers = line.split(" ").map { it.toInt() }
        val layers = mutableListOf(numbers)
        while (layers.last().distinct().size > 1 || layers.last().first() != 0) {
            layers.add(layers.last().zipWithNext().map { (a, b) -> b - a })
        }
        return layers
    }

    fun a(input: String): Int = input.lines().sumOf { line ->
        val layers = layers(line)
        layers.indices.reversed().drop(1).scan(0) { acc, i ->
            layers[i].last() + acc
        }.last()
    }

    fun b(input: String): Int = input.lines().sumOf { line ->
        val layers = layers(line)
        layers.indices.reversed().drop(1).scan(0) { acc, i ->
            layers[i].first() - acc
        }.last()
    }


}