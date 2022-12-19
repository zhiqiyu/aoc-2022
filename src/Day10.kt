
val cyclesToLook = arrayOf(20, 60, 100, 140, 180, 220)

class Machine(var width: Int, var height: Int) {
    var spritePos: Int
    var cycle: Int
    var signalStrength: Int
    var screen: Array<Array<Char>>
    init {
        spritePos = 1
        cycle = 0
        signalStrength = 0
        screen = Array(height) { Array(width) { '.' }}
    }

    private fun maybeRecordSignalStrength(cycle: Int) {
        if (cyclesToLook.contains(cycle)) {
            signalStrength += cycle * spritePos
        }
    }

    private fun cycleToPixel(): Pair<Int, Int> {
        return Pair((cycle-1) / width, (cycle-1).mod(width))
    }

    private fun drawPixel() {
        var (i, j) = cycleToPixel()
        if (arrayOf(spritePos-1, spritePos, spritePos+1).contains(j)) {
            screen.get(i).set(j, '#')
        }
    }

    fun execute(command: List<String>) {
        if (command.size == 1) {
            // noop
            maybeRecordSignalStrength(++cycle)
            drawPixel()
        } else if (command.size == 2) {
            for (i in 0..1) {
                maybeRecordSignalStrength(++cycle)
                drawPixel()
            }
            spritePos += command.get(1).toInt()
        } else {
            throw Error("wrong command")
        }
    }

    fun printScreen() {
        screen.forEach { line -> 
            println(line.joinToString(""))
        }
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        var machine = Machine(40, 6)
        input.map { it -> it.split(" ") }.forEach { command ->
            machine.execute(command)
        }
        return machine.signalStrength
    }

    fun part2(input: List<String>) {
        var machine = Machine(40, 6)
        input.map { it -> it.split(" ")}.forEach { command -> 
            machine.execute(command)
        }
        machine.printScreen()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    part2(testInput)
    // check(part2(testInput) == )

    val input = readInput("Day10")
    println("----------")
    println(part1(input))
    part2(input)
}
