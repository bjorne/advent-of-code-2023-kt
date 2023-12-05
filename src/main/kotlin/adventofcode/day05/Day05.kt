package adventofcode.day05

import adventofcode.shared.LongRangeExtensions.difference
import adventofcode.shared.LongRangeExtensions.intersect
import adventofcode.shared.LongRangeExtensions.offset
import adventofcode.shared.LongRangeExtensions.overlaps
import adventofcode.shared.LongRangeExtensions.unionOverlapping

fun day05a(input: String): Long = input.split("\n\n").let { chunks ->
    val seeds = chunks.first().split(": ").last().split(" ").map(String::toLong)
    chunks.drop(1).fold(seeds) { prev, chunk ->
        val mappings = chunk.split("\n").drop(1).map { line ->
            val (toStart, fromStart, len) = line.split(" ").map(String::toLong)
            Pair(toStart - fromStart, fromStart until fromStart + len)
        }
        prev.map { prevNum ->
            mappings.find { (_, sourceRange) ->
                sourceRange.contains(prevNum)
            }?.let { (offset, sourceRange) ->
                prevNum + offset
            } ?: prevNum
        }
    }.min()
}

fun day05b(input: String): Long = input.split("\n\n").let { chunks ->
    val seeds = chunks
        .first()
        .removePrefix("seeds: ")
        .split(" ")
        .map(String::toLong)
        .chunked(2)
        .map { (a, b) -> a until a + b }

    chunks.drop(1).fold(seeds) { previousRanges, chunk ->
        val mappings = chunk.split("\n").drop(1).map { line ->
            val (toStart, fromStart, len) = line.split(" ").map(String::toLong)
            Pair(toStart - fromStart, fromStart until fromStart + len)
        }
        val mapped = previousRanges.flatMap { prevRange ->
            mappings
                .filter { (_, sourceRange) -> sourceRange.overlaps(prevRange) }
                .map { (offset, sourceRange) ->
                    sourceRange.intersect(prevRange)!!.offset(offset)

                }
                .ifEmpty { listOf(prevRange) }
        }
        val unmapped = mappings.fold(previousRanges) { acc, (_, sourceRange) ->
            acc.flatMap { it.difference(sourceRange) }.unionOverlapping()
        }
        (mapped + unmapped).unionOverlapping()
    }
}.minOf { it.first }
