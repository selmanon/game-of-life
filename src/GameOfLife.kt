data class Cell(val i: Int, val j: Int, val isAlive: Boolean) {
    operator fun get(i: Int, j: Int): Int {
        if ((i < 0 || i >= 2) || (j < 0 || j >= 2))
            return 0
        else
            return 0
    }
}

const val inputEntry = "...**..\n..***..\n"

var nextLineOfCells: Array<Cell?> = Array(7) { Cell(-1, -1, false) }


fun nextGrid(grid: Array<Array<Cell?>?>): Array<Array<Cell?>?> {
    var nextGrid: Array<Array<Cell?>?> = arrayOfNulls(14)

    var neighboursOfLivingCell = 0
    var neighboursOfDeadCell = 0

    for (lineOfCells in grid)
        for (cell in lineOfCells!!) {
            if (cell != null) {
                if (grid[cell.i]!![cell.j]?.isAlive!!) {
                    neighboursOfLivingCell = computeCellNeighbours(grid, cell)

                    if (neighboursOfLivingCell == 2 || neighboursOfLivingCell == 3) {
                        nextLineOfCells[cell.j] = (Cell(cell.i, cell.j, true))
                    } else {
                        nextLineOfCells[cell.j] = (Cell(cell.i, cell.j, false))
                    }
                    System.out.println(nextLineOfCells[cell.j])
                } else {
                    neighboursOfDeadCell = computeCellNeighbours(grid, cell)

                    if (neighboursOfDeadCell == 3) {
                        nextLineOfCells[cell.j] = (Cell(cell.i, cell.j, true))
                    } else {
                        nextLineOfCells[cell.j] = (Cell(cell.i, cell.j, false))
                    }
                    System.out.println(nextLineOfCells[cell.j])
                }
            }
            if (cell != null) {
                nextGrid[cell.i] = nextLineOfCells
            }
            nextLineOfCells = Array(7) { Cell(-1, -1, false) }
        }
    return nextGrid
}


private fun liveCount(i: Int, j: Int) = if (i in 0 until 2 &&
    j in 0 until 2 &&
    inputGrid[i]!![j]!!.isAlive
) 1 else 0

private fun computeCellNeighbours(
    grid: Array<Array<Cell?>?>,
    cell: Cell?
): Int {
    if (cell != null) {

        liveCount(cell.i + 1, cell.j + 1) +
                liveCount(cell.i + 1, cell.j - 1) +
                liveCount(cell.i + 1, cell.j) +
                liveCount(cell.i, cell.j) +
                liveCount(cell.i - 1, cell.j + 1) +
                liveCount(cell.i, cell.j + 1) +
                liveCount(cell.i - 1, cell.j - 1) +
                liveCount(cell.i - 1, cell.j + 1)
    }
    return 0
}

fun stringInputToMatrix(input: String): Array<Array<Cell?>?> {
    val matrix: Array<Array<Cell?>?> = arrayOfNulls(14)

    var lineOfCells: Array<Cell?>? = Array(7) { Cell(-1, -1, false) }

    var lineIndex = 0
    var columnIndex = 0

    for (c: Char in input.iterator()) {
        when (c) {
            '.' -> {
                lineOfCells!![columnIndex] = Cell(lineIndex, columnIndex, false)
                columnIndex++
            }

            '*' -> {
                lineOfCells!![columnIndex] = Cell(lineIndex, columnIndex, true)
                columnIndex++
            }

            '\n' -> {
                matrix[lineIndex] = lineOfCells
                lineIndex++
                columnIndex = 0
                lineOfCells = Array(7) { Cell(-1, -1, false) }
            }
        }
    }

    return matrix
}

var inputGrid: Array<Array<Cell?>?> = arrayOfNulls(14)


fun main() {
    inputGrid = stringInputToMatrix(inputEntry)
    var outputGrid: Array<Array<Cell>> = nextGrid(inputGrid) as Array<Array<Cell>>

}