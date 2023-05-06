val PERMUTATION_ITEMS = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
val DIVISORS = intArrayOf(2, 3, 5, 7, 11, 13, 17)
val allMatchingNumbers = mutableListOf<List<Int>>()
const val FIRST_SUBSTRING_END_INDEX = 4

fun main() {
    generate(emptyList(), PERMUTATION_ITEMS)

    val totalSumOfAllMatchingNumbers = allMatchingNumbers
        .map { it.joinToString("") }
        .map { it.toLong() }
        .reduce{ sum, new -> sum + new }
    println(totalSumOfAllMatchingNumbers)
}

fun generate(prefix: List<Int>, left: List<Int>) {
    if (prefix.size >= FIRST_SUBSTRING_END_INDEX) {
        val sum = prefix[prefix.size - 3] * 100 + prefix[prefix.size - 2] * 10 + prefix[prefix.size - 1] // sum the substring
        if (sum % DIVISORS[prefix.size - FIRST_SUBSTRING_END_INDEX] != 0) {
            return
        }
    }

    if (left.isEmpty()) {
        allMatchingNumbers.add(prefix)
        return
    }

    for (value in left) {
        val newPrefix = prefix.toMutableList()
        newPrefix.add(value)

        generate(newPrefix, left.minus(value))
    }
}