import kotlin.math.max

class SimpleHeap(var data: Array<Int> = arrayOf(0, 0, 0)) {

    fun add(item: Int) {
        data.set(2, max(data.get(2), item))
        reorder()
        return
    }

    fun reorder() {
        var cur = 2
        while (cur > 0 && data.get(cur) > data.get(cur - 1)) {
            val temp = data.get(cur-1)
            data.set(cur-1, data.get(cur))
            data.set(cur, temp)
            cur--
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        var largest = 0
        var cur = 0
        for (line in input) {
            val parsed = line.toIntOrNull()
            if (parsed !== null) {
                cur += parsed
            } else {
                largest = max(largest, cur)
                cur = 0
            }
        }
        return max(largest, cur)
    }

    fun part2(input: List<String>): Int {
        var heap = SimpleHeap()
        var cur = 0
        for (line in input) {
            val parsed = line.toIntOrNull()
            if (parsed !== null) {
                cur += parsed
            } else {
                heap.add(cur)
                cur = 0
            }
        }
        heap.add(cur)
        return heap.data.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println("----------")
    println(part1(input))
    println(part2(input))
}
