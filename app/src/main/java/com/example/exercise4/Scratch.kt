package com.example.exercise4

fun countPalindromicSubstrings(str: String): Int {
    var count = 0
    val len = str.length

    for (i in 0 until len) {
        for (j in i + 3..len) {
            val substring = str.substring(i, j)
            if (substring == substring.reversed()) {
                count++
            }
        }
    }
    return count
}

fun sortWithPalindromeWeight(list: List<String>): List<String> {
    return list.sortedWith(
        compareByDescending<String> { str ->
            if (str.isEmpty()) {
                0.0
            } else {
                countPalindromicSubstrings(str).toDouble() / str.length
            }
        }.thenByDescending { str ->
            str.count { it == 'K' }
        }
    )
}

fun main() {
    val words = listOf(
        "RACECAR",
        "MADAM",
        "KAYAK",
        "KOTLIN",
        "JAVA"
    )

    val sortedWords = sortWithPalindromeWeight(words)

    println("Original List:")
    words.forEach { println(it) }

    println("\nSorted List:")
    sortedWords.forEach { println(it) }
}