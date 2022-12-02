import kotlin.math.max
import kotlin.math.abs

class Game(
    var abc: Array<String> = arrayOf("A", "B", "C"), // rock, paper, scissor
    var xyz: Array<String> = arrayOf("X", "Y", "Z")  // lose, draw, win
    ) { 
    
    var pointMap: MutableMap<String, Int>
    init {
        pointMap = mutableMapOf<String, Int>()
        for (i in abc.indices) {
            for (j in xyz.indices) {
                when (i - j) {
                    -2 -> pointMap.put(abc.get(i).plus(xyz.get(j)), 0)
                    -1 -> pointMap.put(abc.get(i).plus(xyz.get(j)), 6)
                    0 -> pointMap.put(abc.get(i).plus(xyz.get(j)), 3)
                    1 -> pointMap.put(abc.get(i).plus(xyz.get(j)), 0)
                    2 -> pointMap.put(abc.get(i).plus(xyz.get(j)), 6)
                }
            }
        }
    }
    
    fun round(opponent: String, me: String): Int {
        return xyz.indexOf(me) + 1 + pointMap.getValue(opponent.plus(me))
    }

    fun round2(opponent: String, result: String): Int {
        var i = abc.indexOf(opponent)
        var j = xyz.indexOf(result)
        when (j) {
            0 -> return (i-1).mod(3) + 1
            1 -> return i + 1 + 3
            2 -> return (i+1).mod(3) + 1 + 6
        }
        return 0
    }
}

fun main() {
    var game = Game()

    fun part1(input: List<String>): Int {
        var points = 0
        for (line in input) {
            var plays = line.split(" ")
            points += game.round(plays.get(0), plays.get(1))
        }
        return points
    }

    fun part2(input: List<String>): Int {
        var points = 0
        for (line in input) {
            val plays = line.split(" ")
            points += game.round2(plays.get(0), plays.get(1))
        }
        return points
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println("----------")
    println(part1(input))
    println(part2(input))
}
