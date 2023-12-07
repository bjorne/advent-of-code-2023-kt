package adventofcode

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

object Day06 {
    private fun parseInput(input: String): Pair<List<Long>, List<Long>> {
        val (timeRaw, distanceRaw) = input.split("\n")
        val time = "\\d+".toRegex().findAll(timeRaw.split(": ").last()).map { it.value.toLong() }.toList()
        val distance = "\\d+".toRegex().findAll(distanceRaw.split(": ").last()).map { it.value.toLong() }.toList()
        return Pair(time, distance)
    }

    // solve t * (T - t) = D + 1 for t
// t = (T +- sqrt(T^2 - 4D - 4)) / 2
    private fun winningCombos(races: List<Pair<Long, Long>>) = races.map { (time, distance) ->
        val t1 = (time - sqrt(time.toDouble().pow(2) - 4 * (distance + 1))) / 2
        val t2 = (time + sqrt(time.toDouble().pow(2) - 4 * (distance + 1))) / 2
        val t1i = ceil(t1).toLong()
        val t2i = floor(t2).toLong()
        t2i - t1i + 1
    }.reduce(Long::times)

    fun a(input: String): Long {
        val (time, distance) = parseInput(input)
        val races = time.zip(distance)
        return winningCombos(races)
    }

    fun b(input: String): Long {
        val (time, distance) = parseInput(input)
        val races =
            listOf(time.joinToString("") { it.toString() }.toLong() to distance.joinToString("") { it.toString() }
                .toLong())
        return winningCombos(races)
    }
}