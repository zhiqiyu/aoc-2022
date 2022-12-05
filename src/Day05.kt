import kotlin.math.max
import kotlin.math.abs
import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class Stacks() {
    var stacks: ArrayList<ArrayDeque<Char>>
    init {
        stacks = ArrayList<ArrayDeque<Char>>()
    }

    fun initialize(input: List<String>) {
        var nums = (input.get(0).length + 1) / 4 
        for (i in 0..nums-1) stacks.add(ArrayDeque<Char>())
        for (line in input) {
            if (line == "\n") break
            for (j in line.indices) {
                if (line.get(j) == '[') {
                    stacks.get(j/4).add(line.get(j+1))
                }
            }
        }
    }

    fun move(from: Int, to: Int, quant: Int) {
        for (i in 0..quant-1) {
            stacks.get(to-1).addFirst(stacks.get(from-1).removeFirst())
        }
    }

    fun move2(from: Int, to: Int, quant: Int) {
        var temp = ArrayDeque<Char>()
        for (i in 0..quant-1) {
            temp.addFirst(stacks.get(from-1).removeFirst())
        }
        for (i in 0..quant-1) {
            stacks.get(to-1).addFirst(temp.removeFirst())
        }
    }

    fun getTops(): String {
        var tops = stacks.map {it -> it.get(0)}
        return tops.joinToString(separator="")
    }
}

fun main() {

    /*
    [G] [R]                 [P]    
    [H] [W]     [T] [P]     [H]    
    [F] [T] [P] [B] [D]     [N]    
[L] [T] [M] [Q] [L] [C]     [Z]    
[C] [C] [N] [V] [S] [H]     [V] [G]
[G] [L] [F] [D] [M] [V] [T] [J] [H]
[M] [D] [J] [F] [F] [N] [C] [S] [F]
[Q] [R] [V] [J] [N] [R] [H] [G] [Z]
 1   2   3   4   5   6   7   8   9 
     */


    fun part1(input: List<String>): String {
        var stacks = Stacks()
        stacks.initialize(input)
        for (i in 0..(input.size-1)) {
            if (!input.get(i).startsWith("move")) continue
            var parts = input.get(i).split(" ")
            var quant = parts.get(1).toInt()
            var from = parts.get(3).toInt()
            var to = parts.get(5).toInt()
            stacks.move(from, to, quant)
        }
        return stacks.getTops()
    }

    fun part2(input: List<String>): String {
        var stacks = Stacks()
        stacks.initialize(input)
        for (i in 0..(input.size-1)) {
            if (!input.get(i).startsWith("move")) continue
            var parts = input.get(i).split(" ")
            var quant = parts.get(1).toInt()
            var from = parts.get(3).toInt()
            var to = parts.get(5).toInt()
            stacks.move2(from, to, quant)
        }
        return stacks.getTops()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println("----------")
    println(part1(input))
    println(part2(input))
}
