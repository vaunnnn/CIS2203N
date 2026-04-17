package com.example.exercise4

fun String.countVowels(): Int {
    return count { it.lowercaseChar() in "aeiou" }
}

class StateMachine(var currentState: Any) {

    inline fun <reified T : Any> transitionTo(newState: T) {
        val currentName = currentState::class.simpleName ?: ""
        val targetName = T::class.simpleName ?: ""

        val currentVowels = currentName.countVowels()
        val targetVowels = targetName.countVowels()

        if (targetVowels > currentVowels) {
            println("Transition SUCCESS: [$currentName] -> [$targetName]")
            currentState = newState
        } else {
            println("Transition FAILED: [$targetName] ($targetVowels vowels) does NOT have more vowels than [$currentName] ($currentVowels vowels).")
        }
    }
}

class StateMachineBuilder {
    var initialState: Any? = null

    fun build(): StateMachine {
        requireNotNull(initialState) { "Initial state must be configured!" }
        return StateMachine(initialState!!)
    }
}

fun stateMachine(setup: StateMachineBuilder.() -> Unit): StateMachine {
    val builder = StateMachineBuilder()
    builder.setup()
    return builder.build()
}

class Run
class Idle
class Execute
class Sleep

fun main() {
    val machine = stateMachine {
        initialState = Run()
    }

    println("Starting State: ${machine.currentState::class.simpleName}")

    machine.transitionTo(Idle())

    machine.transitionTo(Sleep())

    machine.transitionTo(Execute())

    machine.transitionTo(Run())
}