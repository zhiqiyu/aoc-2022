import java.util.Queue
import java.util.Stack

fun main() {

    fun part1(input: List<String>): List<Int> {
        var result = ArrayList<Int>()
        for (line in input) {
            var queue = ArrayDeque<Char>(0)
            for (i in line.indices) {
                if (queue.size < 4) {
                    queue.addLast(line.get(i))
                }
                if (queue.size == 4) {
                    if (queue.toHashSet().size == 4) {
                        result.add(i + 1)
                        break
                    } else {
                        queue.removeFirst()
                    }
                }
            }
        }
        return result
    }

    fun part2(input: List<String>): List<Int> {
        var result = ArrayList<Int>()
        for (line in input) {
            var queue = ArrayDeque<Char>(0)
            for (i in line.indices) {
                if (queue.size < 14) {
                    queue.addLast(line.get(i))
                }
                if (queue.size == 14) {
                    if (queue.toHashSet().size == 14) {
                        result.add(i + 1)
                        break
                    } else {
                        queue.removeFirst()
                    }
                }
            }
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == listOf(7, 5, 6, 10, 11))
    check(part2(testInput) == listOf(19, 23, 23, 29, 26))

    val input = readInput("Day06")
    println("----------")
    println(part1(input))
    println(part2(input))
}
