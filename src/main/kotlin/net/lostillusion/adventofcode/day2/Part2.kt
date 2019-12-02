package net.lostillusion.adventofcode.day2

import net.lostillusion.adventofcode.utils.ResourceReader

fun main() {
    for(noun in 1..100) {
        for(verb in 1..100) {
            if(getOutputWithInputPairs(noun, verb) == 19690720) {
                println("noun - $noun and verb - $verb equals flag")
            }
        }
    }
}

fun getOutputWithInputPairs(first: Int, second: Int): Int {
    val ops = ResourceReader<Int>("/day2-part1").computeToCollection(mutableListOf(), ",", { _, c -> c.toInt()})
    ops[1] = first
    ops[2] = second
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
    return ops[0]
}