package adventofcode.day01
fun day01a(input: String): Int {
    return input.split("\n").map {
        val numbers = it.filter { it.isDigit() }
        numbers[0].toString().toInt() * 10 + numbers[numbers.length - 1].toString().toInt()
    }.sum()
}

val numberStrings = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val numberRegex = """(?=(\d|${numberStrings.joinToString("|")}))""".toRegex()

fun day01b(input: String): Int {
    return input.split("\n").map {
        val numbers = numberRegex.findAll(it).toList().map { it.groupValues[1] }.map {
            when {
                numberStrings.contains(it) -> numberStrings.indexOf(it) + 1
                else -> it.toInt()
            }
        }.toList()
        numbers[0].toString().toInt() * 10 + numbers[numbers.size - 1].toString().toInt()
    }.sum()
}