package net.lostillusion.adventofcode.day1

import net.lostillusion.adventofcode.utils.ResourceReader
import kotlin.math.floor

fun main() {
    val sum = ResourceReader<Int>("/day1-part1")
        .computeToCollection(mutableListOf(), "\n", { _, content -> content.toInt()})
        .map { floor(it/3.0) -2 }
        .sum()
    println(sum)
}