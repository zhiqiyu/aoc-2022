import kotlin.math.max
import kotlin.math.abs



fun main() {

    fun getPriority(item: Char): Int {
        if (item.isLowerCase()) {
            return item.minus('a') + 1
        } else {
            return item.minus('A') + 27
        }
    }

    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            var seen = hashSetOf<Char>()
            var duplicates = hashSetOf<Char>()
            for (i in line.indices) {
                if (i < line.length / 2) {
                    seen.add(line.get(i))
                } else {
                    if (seen.contains(line.get(i))) {
                        duplicates.add(line.get(i))
                    }
                }
            }
            for (item in duplicates) {
                result += getPriority(item)
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for (i in 0..input.lastIndex step 3) {
            var dup = input.get(i).toMutableList() intersect (input.get(i+1).toMutableList() intersect input.get(i+2).toMutableList()) 
            var badge = dup.iterator().next()
            result += getPriority(badge)
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println("----------")
    println(part1(input))
    println(part2(input))
}
