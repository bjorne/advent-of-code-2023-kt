package adventofcode

import kotlin.math.pow

object Day04 {
    data class Card(val number: Int, val winning: List<Int>, val hand: List<Int>) {
        val wins = hand.intersect(winning).size
    }

    val cardRegex = """Card +(\d+): (.+) \| (.+)""".toRegex()
    val numberRegex = "(\\d+)".toRegex()

    private fun parseInput(input: String) = input.lines().map { line ->
        val (number, winning, hand) = cardRegex.find(line)!!.destructured
        val winningParsed = numberRegex.findAll(winning).map(MatchResult::value).map(String::toInt)
        val handParsed = numberRegex.findAll(hand).map(MatchResult::value).map(String::toInt)
        Card(number.toInt(), winningParsed.toList(), handParsed.toList())
    }

    fun a(input: String): Int =
        parseInput(input).sumOf {
            if (it.wins > 0) 2.toDouble().pow(it.wins).toInt() / 2 else 0
        }


    fun b(input: String): Int = parseInput(input).let { cards ->
        fun recurse(card: Card, cache: MutableMap<Int, Int> = mutableMapOf()): Int {
            return 1 + cards.slice(card.number until card.number + card.wins).sumOf {
                cache.getOrPut(it.number) { recurse(it, cache) }
            }
        }
        return cards.sumOf(::recurse)
    }

    fun b2(input: String): Int = parseInput(input).let { cards ->
        val scores = List(cards.size) { 0 }.toMutableList()
        for (i in cards.size - 1 downTo 0) {
            scores[i] = 1 + cards.slice(i until i + cards[i].wins).sumOf {
                scores[it.number]
            }
        }
        scores.sum()
    }
}