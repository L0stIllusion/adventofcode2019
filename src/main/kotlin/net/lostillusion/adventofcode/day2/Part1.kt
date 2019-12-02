package net.lostillusion.adventofcode.day2

import net.lostillusion.adventofcode.utils.ResourceReader

fun main() {
    val ops = ResourceReader<Int>("/day2-part1").computeToCollection(mutableListOf(), ",", { _, c -> c.toInt()})
    ops[1] = 12
    ops[2] = 2
    var currentOp: OpCode? = null
    for(op in ops) {
        //if this is a number for a opcode to eval or set output
        if(currentOp != null) {
            when {
                currentOp.getFirstNumber() == -1 -> currentOp.setFirstNumber(ops[op])
                currentOp.getSecondNumber() == -1 -> currentOp.setSecondNumber(ops[op])
                else -> {
                    //set output to the position which this op refers to
                    ops[op] = currentOp.evaluate()
                    //reset currentOp
                    currentOp = null
                }
            }
            continue
        }
        val op = OpCode.byCode(op)
        if(op != null) {
            if(op is OpCode.Finished) break
            currentOp = op
            continue
        }
    }
    println(ops[0])
}

sealed class OpCode {
    class Add: OpCode() {
        override fun evaluate(numbers: Pair<Int, Int>) = numbers.first + numbers.second
    }

    class Multiply: OpCode() {
        override fun evaluate(numbers: Pair<Int, Int>): Int = numbers.first * numbers.second
    }

    class Finished: OpCode() {
        @Throws(IllegalStateException::class)
        override fun evaluate(numbers: Pair<Int, Int>): Int {
            throw IllegalStateException("dont evaluate finish opcode")
        }
    }

    private var pair: Pair<Int, Int> = Pair(-1, -1)

    fun setFirstNumber(first: Int) {
        pair = Pair(first, pair.second)
    }

    fun setSecondNumber(second: Int) {
        pair = Pair(pair.first, second)
    }

    fun getFirstNumber() = pair.first

    fun getSecondNumber() = pair.second

    fun evaluate(): Int = evaluate(pair)

    abstract fun evaluate(numbers: Pair<Int, Int>): Int

    companion object {
        fun byCode(code: Int): OpCode? {
            return when(code) {
                1 -> Add()
                2 -> Multiply()
                99 -> Finished()
                else -> null
            }
        }
    }
}