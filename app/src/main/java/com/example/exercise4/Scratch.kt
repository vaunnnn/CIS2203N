package com.example.exercise4

import kotlin.math.abs

fun alternatingSumWithTwist(numbers: List<Int>): Int {
    var total = 0

    numbers.forEachIndexed { index, number ->

        val processedNumber = if (number % 7 == 0 && number != 0) {
            abs(number).toString().sumOf { it.digitToInt() }
        } else {
            number
        }

        if (index % 2 == 0) {
            total += processedNumber
        } else {
            total -= processedNumber
        }
    }

    return total
}

fun main() {
    val testList = listOf(10, 14, 5, 21)
    val result = alternatingSumWithTwist(testList)

    println("The final alternating sum is: $result")
}