package net.lostillusion.adventofcode.day1

import net.lostillusion.adventofcode.utils.ResourceReader
import kotlin.math.floor
import kotlin.math.roundToInt

fun main() {
    val sum = ResourceReader<Int>("/day1-part1")
        .computeToCollection(mutableListOf(), { _, content -> content.toInt() })
        .map(::handleFuelFormula)
        .sum()
    println(sum)
}

fun handleFuelFormula(mass: Int): Int {
    val requiredFuel = mutableListOf<Int>()
    var currentFuel = mass
    while(getFuel(currentFuel) > 0) {
        val newFuel = getFuel(currentFuel)
        requiredFuel.add(newFuel)
        currentFuel = newFuel
    }
    return requiredFuel.sum()
}

fun getFuel(fuel: Int): Int {
    return (floor(fuel/3.0) -2).roundToInt()
}
