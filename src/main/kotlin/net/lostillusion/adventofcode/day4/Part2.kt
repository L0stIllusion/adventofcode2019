package net.lostillusion.adventofcode.day4

fun main() {
    val passwords = findPasswordsWithRequirements(
        SixDigitPasswordRequirement(),
        //puzzle input
        InRangePasswordRequirement(382345..843167),
        StrictAdjacentDigitPasswordRequirement(),
        IncreasingDigitPasswordRequirement()
    )
    println(passwords.size)
}