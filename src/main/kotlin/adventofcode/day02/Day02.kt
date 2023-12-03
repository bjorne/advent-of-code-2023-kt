package adventofcode.day02

import java.lang.Integer.max

val max = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
)
fun day02a(input: String): Int {
    return input.split("\n").map {
        val (game, contents) = it.split(":")
        val isPossible = contents.split(";").all {
            it.split(", ").all {
                val (count, color) = it.trim().split(" ")
                count.toInt() <= max[color]!!
            }
        }
        Pair(game.split(" ").last().toInt(), isPossible)
    }.filter { it.second }.map { it.first }.sum()
}

fun day02b(input: String): Int {
    return input.split("\n").map {
        val (_, contents) = it.split(":")
        val highest = contents.split(";").foldRight(emptyMap<String,Int>()) { str, acc ->
            acc + str.split(", ").map {
                val (count, color) = it.trim().split(" ")
                color to max( acc.getOrDefault(color, 0), count.toInt())
            }.toMap()
        }
        highest.values.reduce(Int::times)
    }.sum()
}