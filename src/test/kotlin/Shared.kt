package adventofcode.shared
fun getInput(day: Int): String =
    Thread
        .currentThread()
        .contextClassLoader
        .getResource("Day${String.format("%02d", day)}.txt")!!
        .readText()

