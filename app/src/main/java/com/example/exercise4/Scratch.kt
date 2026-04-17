package com.example.exercise4

fun greetUser(name: String): String {
    return "Hello there, $name! Welcome to Kotlin."
}

fun main() {
    // Calling the function and printing the result
    val greetingMessage = greetUser("Charles")
    println(greetingMessage)
}