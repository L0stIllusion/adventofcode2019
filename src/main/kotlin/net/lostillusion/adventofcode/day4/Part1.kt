package net.lostillusion.adventofcode.day4

fun main() {
    val passwords = findPasswordsWithRequirements(
        SixDigitPasswordRequirement(),
        //puzzle input
        InRangePasswordRequirement(382345..843167),
        AdjacentDigitPasswordRequirement(),
        IncreasingDigitPasswordRequirement()
    )
    println(passwords.size)
}

fun findPasswordsWithRequirements(vararg requirements: PasswordRequirement): List<String> {
    val passwords = mutableListOf<String>()
    val chain = PasswordRequirementChain(*requirements)
    for(password in 100000..999999) {
        val pass = password.toString()
        if(chain.checkPassword(pass)) passwords.add(pass)
    }
    return passwords
}