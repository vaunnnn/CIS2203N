package com.example.exercise4

fun chronoDecoder(input: String): Map<Char, Int> {
    if (input.isBlank()) return emptyMap()
    val regex = Regex("([A-Z])([+\\-*/])(\\d+)")

    val operations = input.split(",").mapNotNull { part ->
        val match = regex.find(part.trim())
        if (match != null) {
            val (variable, operator, operand) = match.destructured
            Triple(variable[0], operator[0], operand.toInt())
        } else {
            null
        }
    }

    val sortedOperations = operations.sortedBy { it.first }

    val finalValues = mutableMapOf<Char, Int>()

    for ((variable, operator, operand) in sortedOperations) {
        val currentValue = finalValues.getOrDefault(variable, 1)

        val newValue = when (operator) {
            '+' -> currentValue + operand
            '-' -> currentValue - operand
            '*' -> currentValue * operand
            '/' -> currentValue / operand
            else -> currentValue
        }

        finalValues[variable] = newValue
    }

    return finalValues
}

fun main() {

    val testString = "A+3, C-2, B*2"
    val result = chronoDecoder(testString)

    println("Original String: $testString")
    println("Decoded Map: $result")
}