package net.lostillusion.adventofcode.day4

class PasswordRequirementChain(vararg requirements: PasswordRequirement) {
    private val passwordRequirements: MutableSet<PasswordRequirement> = mutableSetOf()

    init {
        addPasswordRequirements(*requirements)
    }

    fun addPasswordRequirements(vararg requirements: PasswordRequirement) {
        passwordRequirements.addAll(requirements)
    }

    fun checkPassword(password: String): Boolean =
        passwordRequirements.all { requirement -> requirement.testPassword(password) }
}