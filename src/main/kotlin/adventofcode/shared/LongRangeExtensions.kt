package adventofcode.shared

object LongRangeExtensions {
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
}
