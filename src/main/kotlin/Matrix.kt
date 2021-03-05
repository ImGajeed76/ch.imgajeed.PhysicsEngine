class Matrix(m: Array<Array<Float>>) {
    var matrix: Array<Array<Float>> = m

    operator fun get(row: Int, column: Int): Float {

        return matrix[row][column]
    }

    operator fun get(row: Int): Array<Float> {

        return matrix[row]
    }

    fun matmul(matrix1: Matrix): Matrix {
        val matrixOut = Array(size = matrix1.rows, init = { Array(size = matrix[0].size, init = { 0f }) })


        if (matrix1.columns != matrix.size) {
            println("Columns of matrix 1 must match rows of current matrix")
            return Matrix(emptyArray())
        }

        for (i in 0 until matrix1.rows) {
            for (j in matrix[0].indices) {
                for (k in 0 until matrix1.columns) {
                    matrixOut[i][j] += matrix1.get(i, k) * matrix[k][j]
                }
            }
        }

        return Matrix(matrixOut)
    }

    val rows: Int
        get() {
            return matrix.size
        }

    val columns: Int
        get() {
            return matrix[0].size
        }

    fun add(row: Int, column: Int, float: Float) {
        matrix[row][column] += float
    }

    fun subtract(row: Int, column: Int, float: Float) {
        matrix[row][column] -= float
    }

    fun multiply(row: Int, column: Int, float: Float) {
        matrix[row][column] *= float
    }

    fun divide(row: Int, column: Int, float: Float) {
        matrix[row][column] /= float
    }

    fun apply(array: Array<Float>) {
        matrix.apply { array }
    }

    fun apply(row: Int, float: Float) {
        matrix[row].apply { float }
    }

    fun set(row: Int, column: Int, float: Float){
        matrix[row][column] = float
    }

    val toString: String
        get() {
            var out = ""

            for (row in matrix) {
                out += "{ "
                for (column in row) {
                    out += "$column, "
                }
                out = out.slice(0..out.length - 3)
                out += " }\n"
            }

            return out
        }
}