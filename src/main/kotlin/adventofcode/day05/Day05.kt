package adventofcode.day05

fun LongRange.overlaps(other: LongRange): Boolean = this.first <= other.last && other.first <= this.last
fun LongRange.intersect(other: LongRange): LongRange? {
    val first = maxOf(this.first, other.first)
    val last = minOf(this.last, other.last)
    return if (first <= last) first..last else null
}

fun LongRange.union(other: LongRange): LongRange? =
    if (overlaps(other)) minOf(this.first, other.first)..maxOf(this.last, other.last) else null

fun LongRange.offset(offset: Long): LongRange = (this.first + offset)..(this.last + offset)
fun LongRange.difference(other: LongRange): List<LongRange> {
    val intersection = this.intersect(other)
    return if (intersection == null) listOf(this) else listOf(
        this.first until intersection.first,
        intersection.last + 1..this.last
    ).filter { !it.isEmpty() }
}

fun List<LongRange>.unionOverlapping(): List<LongRange> = this
    .sortedBy { it.first }
    .fold(mutableListOf<LongRange>()) { merged, next ->
        val current = merged.lastOrNull()
        if (current?.overlaps(next) == true) {
            merged[merged.size - 1] = current.union(next)!!
        } else {
            merged.add(next)
        }
        merged
    }

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
