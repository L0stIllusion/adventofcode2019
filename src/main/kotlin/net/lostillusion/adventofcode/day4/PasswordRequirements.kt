package net.lostillusion.adventofcode.day4

import java.util.stream.Collectors

interface PasswordRequirement {
    fun testPassword(input: String): Boolean
}

class SixDigitPasswordRequirement: PasswordRequirement {
    override fun testPassword(input: String): Boolean = input.length == 6
}

class InRangePasswordRequirement(private val range: IntRange): PasswordRequirement {
    override fun testPassword(input: String): Boolean = range.contains(input.toInt())
}

class AdjacentDigitPasswordRequirement: PasswordRequirement {
    override fun testPassword(input: String): Boolean {
        return input.split("")
            .filterNot(String::isEmpty)
            .groupingBy(String::toInt)
            .eachCount()
            .values
            .any { it >= 2 }
    }
}

class StrictAdjacentDigitPasswordRequirement: PasswordRequirement {
    override fun testPassword(input: String): Boolean {
        return input.split("")
            .filterNot(String::isEmpty)
            .groupingBy(String::toInt)
            .eachCount()
            .values
            .contains(2)
    }
}

class IncreasingDigitPasswordRequirement: PasswordRequirement {
    override fun testPassword(input: String): Boolean {
        return input.split("")
            .filter { it.isNotEmpty() }
            .stream()
            .sorted()
            .collect(Collectors.joining()) == input
    }
}