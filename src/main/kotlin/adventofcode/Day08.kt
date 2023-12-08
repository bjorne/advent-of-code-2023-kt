package adventofcode

object Day08 {
    fun a(input: String): Int {
        val (turns, map) = parseInput(input)
        val (count, _) = findLength(turns, map)
        return count.toInt()
    }

    fun findLength(
        turns: String,
        map: Map<String, Pair<String, String>>,
        start: String = "AAA",
        end: Regex = "ZZZ".toRegex(),
        startI: Long = 0
    ): Pair<Long, String> {
        var i = startI
        var next = start
        while (true) {
            val turn = turns[(i++ % turns.length.toLong()).toInt()]
            val (left, right) = map[next]!!
            when (turn) {
                'L' -> next = left
                'R' -> next = right
            }
            if (end.matches(next)) {
                return (i - startI) to next
            }
        }
    }

    private fun parseInput(input: String): Pair<String, Map<String, Pair<String, String>>> {
        val (turns, mapStr) = input.split("\n\n")
        val map = mapStr.lines().map { line ->
            val (from, left, right) = "([A-Z0-9]+) = \\(([A-Z0-9]+), ([A-Z0-9]+)\\)".toRegex()
                .matchEntire(line)!!.destructured
            from to (left to right)
        }.toMap()
        return turns to map
    }

    private val endRegex = "..Z".toRegex()

    private fun gcd(a: Long, b: Long): Long {
        var a = a
        var b = b
        while (b > 0) {
            val temp = b
            b = a % b // % is remainder
            a = temp
        }
        return a
    }

    private fun gcd(input: List<Long>): Long {
        var result = input[0]
        for (i in 1 until input.size) result = gcd(result, input[i])
        return result
    }

    private fun lcm(a: Long, b: Long): Long {
        return a * (b / gcd(a, b))
    }

    private fun lcm(input: List<Long>): Long {
        var result = input[0]
        for (i in 1 until input.size) result = lcm(result, input[i])
        return result
    }

    fun b(input: String): Long {
        val (turns, map) = parseInput(input)

        val iterations = map.keys.filter { "..A".toRegex().matches(it) }
            .map { findLength(turns, map, it, "..Z".toRegex(), 0).first }
        return lcm(iterations)
    }

    // takes some time but works
    fun bBrute(input: String): Long {
        val (turns, map) = parseInput(input)

        val (iter, pos) = map.keys.filter { "..A".toRegex().matches(it) }
            .map { findLength(turns, map, it, "..Z".toRegex(), 0) }.unzip()
        val iterations = iter.toMutableList()
        val positions = pos.toMutableList()
        val cache = mutableMapOf<Pair<Long, String>, Pair<Long, String>>()

        while (iterations.distinct().size > 1) {
            val maxIterations = iterations.max()!!
            for (i in positions.indices) {
                while (iterations[i] < maxIterations) {
                    val (length, pos) = cache.getOrPut((iterations[i] % turns.length) to positions[i]) {
                        findLength(turns, map, positions[i], endRegex, iterations[i])
                    }
                    iterations[i] = iterations[i] + length
                    positions[i] = pos
                }
            }
        }
        return iterations.first()
    }
}
