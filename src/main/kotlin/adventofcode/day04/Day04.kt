package adventofcode.day04

import kotlin.math.pow

data class Card(val number: Int, val winning: List<Int>, val hand: List<Int>) {
    fun wins() = hand.intersect(winning).size
}

val cardRegex = """Card +(\d+): (.+) \| (.+)""".toRegex()
val numberRegex = "(\\d+)".toRegex()

private fun parseInput(input: String) = input.lines().map { line ->
    val (number, winning, hand) = cardRegex.find(line)!!.destructured
    val winningParsed = numberRegex.findAll(winning).map(MatchResult::value).map(String::toInt)
    val handParsed = numberRegex.findAll(hand).map(MatchResult::value).map(String::toInt)
    Card(number.toInt(), winningParsed.toList(), handParsed.toList())
}

fun day04a(input: String): Int {
    val cards = parseInput(input)

    return cards.sumOf {
        val wins = it.wins()
        if (wins > 0) 2.toDouble().pow(wins).toInt() / 2 else 0
    }
}

fun day04b(input: String): Int {
    val cards = parseInput(input)
    fun recurse(card: Card, cache: MutableMap<Int, Int> = mutableMapOf()): Int {
        val wins = card.wins()
        return 1 + cards.slice(card.number until card.number + wins).sumOf {
            cache.getOrPut(it.number) { recurse(it, cache) }
        }
    }
    return cards.sumOf(::recurse)

}