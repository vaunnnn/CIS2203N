package com.example.exercise4

fun bouncingSequence(start: Long): Sequence<Long> = sequence {
    var current = start
    val history = mutableListOf<Long>()
    var count = 1

    while (true) {
        if (count > 1 && count % 5 == 0) {
            current = history.sum()
        } else if (count > 1) {
            val prev = history.last()
            current = if (prev % 2 == 0L) prev / 2 else prev * 3 + 1
        }

        history.add(current)
        if (history.size > 4) {
            history.removeAt(0)
        }

        yield(current)

        if (current % 13L == 0L) {
            break
        }

        count++
    }
}

fun main() {
    val mySequence = bouncingSequence(10L)

    println(mySequence.toList())
}