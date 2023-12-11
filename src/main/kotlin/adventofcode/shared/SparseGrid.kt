package adventofcode.shared

import kotlin.math.absoluteValue

data class SparseGrid<V>(val points: Map<Point, V>) {

    fun bounds(p: Point) = p.x in xRange && p.y in yRange
    operator fun get(point: Point) = points[point]

    val minX = points.keys.map { it.x }.minOrNull()!!
    val maxX = points.keys.map { it.x }.maxOrNull()!!
    val minY = points.keys.map { it.y }.minOrNull()!!
    val maxY = points.keys.map { it.y }.maxOrNull()!!
    val width = maxX - minX + 1
    val height = maxY - minY + 1
    val xRange = minX..maxX
    val yRange = minY..maxY
    fun draw(
        xSpace: String? = " ",
        emptyString: String = ".",
        ticks: Boolean = true,
        valueString: (V) -> String? = { it.toString() }
    ): String {
        val sb = StringBuilder()
        for (y in yRange.reversed()) {
            if (ticks) sb.append(y.toString().padStart(5, ' ') + " ")
            for (x in xRange) {
                sb.append(points[Point(x, y)]?.let(valueString) ?: emptyString)
                xSpace?.also { sb.append(it) }
            }
            if (y != yRange.first) sb.append("\n")
        }
        if (ticks) {
            sb.append("\n")
            val sbTick = StringBuilder()
            val sbTickNum = StringBuilder()
            if (xRange.first % 5 != 0) {
                sbTick.append(" ".repeat((xRange.first % 5).absoluteValue + 1))
                sbTickNum.append(" ".repeat((xRange.first % 5).absoluteValue + 1))
            }
            for (x in (xRange.first + (xRange.first % 5).absoluteValue)..(xRange.last) step 5) {
                sbTick.append("|".padStart(if (x == xRange.first) 7 else 2 * 5, ' '))
                sbTickNum.append(x.toString().padStart(if (x == xRange.first) 7 else 2 * 5, ' '))
            }
            sb.append(sbTick.toString() + "\n")
            sb.append(sbTickNum.toString())
        }
        return sb.toString()
    }
}
