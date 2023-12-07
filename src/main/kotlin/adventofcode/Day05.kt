package adventofcode

import adventofcode.shared.LongRangeExtensions.difference
import adventofcode.shared.LongRangeExtensions.intersect
import adventofcode.shared.LongRangeExtensions.offset
import adventofcode.shared.LongRangeExtensions.unionOverlapping

object Day05 {
    fun a(input: String): Long = input.split("\n\n").let { chunks ->
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

    fun b(input: String): Long = input.split("\n\n").let { chunks ->
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
                mappings.mapNotNull { (offset, sourceRange) ->
                    sourceRange.intersect(prevRange)?.offset(offset)
                }
            }
            val unmapped = mappings.fold(previousRanges) { acc, (_, sourceRange) ->
                acc.flatMap { it.difference(sourceRange) }.unionOverlapping()
            }
            (mapped + unmapped).unionOverlapping()
        }
    }.minOf { it.first }
}