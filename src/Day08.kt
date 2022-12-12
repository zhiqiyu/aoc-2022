import kotlin.math.max

class Grid(input: List<String>) {
    var m: Int
    var n: Int
    private val grid: List<List<Int>>
    var visible: MutableList<MutableList<Boolean>>

    init {
        m = input.size
        n = input.get(0).length
        grid = input.map { it.toList().map(Char::digitToInt) }
        visible = MutableList(m) { MutableList(n) {false}}
    }

    fun getVisibleTrees() {
        for (i in 0..m-1) {
            var curHighest = -1
            for (j in 0..n-1) {
                curHighest = compare(i, j, curHighest)
            }
            curHighest = -1
            for (j in n-1 downTo 0) {
                curHighest = compare(i, j, curHighest)
            }
        }
        for (j in 0..n-1) {
            var curHighest = -1
            for (i in 0..m-1) {
                curHighest = compare(i, j, curHighest)
            }
            curHighest = -1 
            for (i in m-1 downTo 0) {
                curHighest = compare(i, j, curHighest)
            }
        }
    }

    private fun compare(i: Int, j: Int, curHighest: Int): Int {
        var local = curHighest
        if (grid.get(i).get(j) > local) {
            local = grid.get(i).get(j)
            visible.get(i).set(j, true)
        }
        return local
    }

    fun getScenicScore(i: Int, j: Int): Int {
        if (i == 0 || j == 0 || i == m-1 || j == n-1) return 0
        var result = 1
        var count = 0
        var height = grid.get(i).get(j)
        for (p in i-1 downTo 0) {
            count += 1
            if (grid.get(p).get(j) >= height) {
                break
            }
        }
        result *= count
        count = 0
        for (p in i+1..m-1) {
            count += 1
            if (grid.get(p).get(j) >= height) {
                break
            }
        }
        result *= count
        count = 0
        for (q in j-1 downTo 0) {
            count += 1
            if (grid.get(i).get(q) >= height) {
                break
            }
        }
        result *= count
        count = 0
        for (q in j+1..n-1) {
            count += 1
            if (grid.get(i).get(q) >= height) {
                break
            }
        }
        result *= count
        return result
    }

    fun getMaxScenicScore(): Int {
        var maxScore = 0
        for (i in 0..m-1) {
            for (j in 0..n-1) {
                maxScore = max(maxScore, getScenicScore(i, j))
            }
        }
        return maxScore
    }

    fun countVisible(): Int {
        return visible.map { it -> it.map { v -> if (v) 1 else 0}.sum()}.sum()
    }

    fun print() {
        println(grid)
    }
}


fun main() {

    fun part1(input: List<String>): Int {
        var grid = Grid(input)
        grid.getVisibleTrees()
        return grid.countVisible()
    }

    fun part2(input: List<String>): Int {
        var grid = Grid(input)
        return grid.getMaxScenicScore()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    print(part2(testInput))
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println("----------")
    println(part1(input))
    println(part2(input))
}
