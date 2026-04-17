package com.example.exercise4

data class Alien(var name: String?, var age: Int?)

fun processAliens(aliens: List<Alien>) {
    for (alien in aliens) {
        val currentName = alien.name
        val currentAge = alien.age

        if (currentName == null && currentAge != null && currentAge % 2 == 0) {
            alien.name = "Zog-$currentAge"
        }

        if (currentAge == null && currentName != null && currentName.length == 4) {
            alien.age = currentName[0].code
        }
    }
}

fun main() {
    val alienList = listOf(
        Alien(null, 24),
        Alien(null, 25),
        Alien("Thor", null),
        Alien("Bob", null),
        Alien("Zeno", 50)
    )

    processAliens(alienList)

    for (alien in alienList) {
        println(alien)
    }
}