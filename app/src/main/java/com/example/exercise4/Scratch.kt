package com.example.exercise4

import kotlinx.coroutines.*

class TimeParadoxException(message: String) : Exception(message)

data class GraphJob(
    val id: String,
    val dependencies: List<String>,
    val action: suspend () -> Unit
)

suspend fun executeDependencyGraph(jobs: List<GraphJob>) = coroutineScope {
    val graph = jobs.associateBy { it.id }

    val visiting = mutableSetOf<String>()
    val visited = mutableSetOf<String>()

    fun detectCycle(nodeId: String) {
        if (visiting.contains(nodeId)) {
            throw TimeParadoxException("Time Paradox detected! Circular deadlock involving Job: $nodeId")
        }
        if (visited.contains(nodeId)) return

        visiting.add(nodeId)

        val node = graph[nodeId] ?: return
        for (depId in node.dependencies) {
            detectCycle(depId)
        }

        visiting.remove(nodeId)
        visited.add(nodeId)
    }

    for (job in jobs) {
        detectCycle(job.id)
    }

    println("Graph is safe. Launching coroutines...")
    val deferredJobs = mutableMapOf<String, Deferred<Unit>>()

    fun startJob(nodeId: String): Deferred<Unit> {
        deferredJobs[nodeId]?.let { return it }

        val deferred = async(start = CoroutineStart.LAZY) {
            val node = graph[nodeId] ?: return@async

            for (depId in node.dependencies) {
                startJob(depId).await()
            }

            node.action()
        }

        deferredJobs[nodeId] = deferred
        return deferred
    }

    val allTasks = jobs.map { startJob(it.id) }
    allTasks.awaitAll()
    println("All jobs completed successfully.")
}

fun main() = runBlocking {

    val safeJobs = listOf(
        GraphJob("A", emptyList()) {
            delay(100); println("Job A Done")
        },
        GraphJob("B", listOf("A")) {
            delay(100); println("Job B Done (relied on A)")
        },
        GraphJob("C", listOf("A", "B")) {
            delay(100); println("Job C Done (relied on A & B)")
        }
    )

    val deadlockedJobs = listOf(
        GraphJob("X", listOf("Z")) { println("Job X") },
        GraphJob("Y", listOf("X")) { println("Job Y") },
        GraphJob("Z", listOf("Y")) { println("Job Z") }
    )

    println("--- Testing Safe Graph ---")
    executeDependencyGraph(safeJobs)

    println("\n--- Testing Deadlocked Graph ---")
    try {
        executeDependencyGraph(deadlockedJobs)
    } catch (e: TimeParadoxException) {
        println("Caught Exception: ${e.message}")
    }
}