package adventofcode.day07

import kotlin.math.pow

data class Hand(val cards: String, val bet: Int) {
    companion object {
        val values = listOf(
            "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"
        )

        fun type(hand: Hand): Long = hand.run {
            val counts = cards.groupBy { it }.map { it.value.size to it.key }.groupBy { it.first }.toMap()
                .mapValues { it.value.size }
            when {
                counts[5] != null -> 7
                counts[4] != null -> 6
                counts[3] != null && counts[2] != null -> 5
                counts[3] != null -> 4
                counts[2] == 2 -> 3
                counts[2] == 1 -> 2
                counts[1] == 5 -> 1
                else -> 0
            }
        }

        fun orderingScore(hand: Hand, values: List<String>): Long = hand.run {
            cards.split("")
                .mapIndexed { index, v -> (values.size - values.indexOf(v)) * 100.toDouble().pow(5 - index).toLong() }
                .reduce(Long::plus)
        }
    }

    object WithJoker {
        val values = listOf(
            "A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J"
        )
        private const val joker = "J"


        fun type(hand: Hand): Long = hand.run {
            if (cards == joker.repeat(5)) {
                Hand.type(hand)
            } else {
                val uniqueCards = cards.split("").distinct() - listOf(joker)
                uniqueCards.maxOf { Hand.type(Hand(cards.replace(joker, it), bet)) }
            }
        }
    }


}

object Day07 {
    private fun run(input: String, typeFun: (hand: Hand) -> Long, values: List<String>) = input.lines().map { line ->
        val (cards, bet) = line.split(" ")
        Hand(cards, bet.toInt())
    }.let { hands ->
        hands
            .sortedBy { 100.toDouble().pow(7).toLong() * typeFun(it) + Hand.orderingScore(it, values) }
            .mapIndexed { index, hand ->
                hand.bet * (index + 1)
            }
            .sum()
    }

    fun a(input: String): Int = run(input, Hand::type, Hand.values)

    fun b(input: String): Int = run(input, Hand.WithJoker::type, Hand.WithJoker.values)
}
