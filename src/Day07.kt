import kotlin.math.min

class InputParser(var input: List<String>, var root: DirTree) {
    var cursor: DirTree
    init {
        cursor = root
    }
    fun parse() {
        for (line in input) {
            if (line.startsWith('$')) {
                // command
                var commands = line.split(" ")
                if (commands.get(1) == "cd") {
                    when (commands.get(2)) {
                        ".." -> cursor = cursor.parent!!
                        "/" -> cursor = root.members.get("/")!!
                        else -> cursor = cursor.members.get(commands.get(2))!!
                    }
                }
            } else {
                var parts = line.split(" ")
                assert(parts.size == 2)
                if (!cursor.members.containsKey(parts.get(1))) {
                    if (parts.get(0) == "dir") {
                        cursor.addMember(DirTree(parts.get(1), 0, cursor))
                    } else {
                        cursor.addMember(DirTree(parts.get(1), parts.get(0).toInt(), cursor))
                    }
                }
            }
        }
    }
}

class DirTree(var name: String, var size: Int, var parent: DirTree?) {
    var members: MutableMap<String, DirTree>
    init {
        members = HashMap<String, DirTree>()
    }

    fun addMember(item: DirTree) {
        members.put(item.name, item)
    }

    fun calculateSize(): Int {
        if (size > 0) {
            return size
        }
        var result = 0
        for (dir in members.values) {
            result += dir.calculateSize()
        }
        size = -1 * result
        return result
    }
    
    fun print(numSpaces: Int = 0): String {
        var result = " ".repeat(numSpaces).plus("- ${name} (${if (size <= 0) "dir, size=".plus(size) else "file, size=".plus(size)})").plus("\n")
        for (dir in members.values) {
            result += dir.print(numSpaces+2)
        }
        return result
    }
}

fun main() {
    var result = 0
    var result2 = Int.MAX_VALUE
    var spaceNeeded = Int.MAX_VALUE

    fun traverse(root: DirTree) {
        if (root.size <= 0 && root.size >= -100000) {
            result += -root.size
        }
        if (root.size <= 0 && root.size <= -spaceNeeded) {
            result2 = min(result2, -root.size)
        }
        for (dir in root.members.values) {
            if (dir.size <= 0) {
                traverse(dir)
            }
        }
    }

    fun solution(input: List<String>) {
        result = 0
        result2 = Int.MAX_VALUE
        var tree = DirTree("root", 0, null)
        tree.addMember(DirTree("/", 0, tree))
        var parser = InputParser(input, tree)
        parser.parse()
        tree.calculateSize()
        spaceNeeded = 30000000 - (70000000 + tree.size)
        // print(tree.print())
        traverse(tree)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    solution(testInput)
    check(result == 95437)
    check(result2 == 24933642)

    val input = readInput("Day07")
    solution(input)
    println("----------")
    println(result)
    println(result2)
}
