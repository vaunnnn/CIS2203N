package com.example.exercise4

fun String.vowelShift(): String {
    if (this.length % 2 == 0) {
        return this
    }
    val regex = Regex("(?i)([bcdfghjklmnpqrstvwxyz])([aeiou])(\\1)")

    return regex.replace(this) { matchResult ->
        val consonant1 = matchResult.groupValues[1]
        val vowel = matchResult.groupValues[2].uppercase()
        val consonant2 = matchResult.groupValues[3]

        val newBlock = "$consonant1$vowel$consonant2"

        "$newBlock$newBlock"
    }
}

fun main() {
    val oddString = "radar"
    val evenString = "raddar"
    val test1 = "xbabx"

    println(test1.vowelShift())
    println(evenString.vowelShift())
}