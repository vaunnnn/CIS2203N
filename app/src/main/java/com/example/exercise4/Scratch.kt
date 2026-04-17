package com.example.exercise4

data class User(val username: String, var score: Int)

fun main() {
    val userList = listOf(
        User("Charles", 1500),
        User("PlayerTwo", 1200),
        User("PlayerThree", 950)
    )

    for (user in userList) {
        println(user)
    }
}