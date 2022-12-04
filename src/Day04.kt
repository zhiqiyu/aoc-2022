import kotlin.math.max
import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            var pairs = line.split(",").map { it.split("-") }
            var s1 = pairs.get(0).get(0).toInt()
            var e1 = pairs.get(0).get(1).toInt()
            var s2 = pairs.get(1).get(0).toInt()
            var e2 = pairs.get(1).get(1).toInt()
            if ((s1 <= s2 && e1 >= e2) || (s1 >= s2 && e1 <= e2)) {
                result ++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for (line in input) {
            var pairs = line.split(",").map { it.split("-") }
            var s1 = pairs.get(0).get(0).toInt()
            var e1 = pairs.get(0).get(1).toInt()
            var s2 = pairs.get(1).get(0).toInt()
            var e2 = pairs.get(1).get(1).toInt()
            if (!(e1 < s2 || s1 > e2)) {
                result ++
            }
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println("----------")
    println(part1(input))
    println(part2(input))
}
