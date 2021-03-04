class Matrix(m: Array<Array<Float>>) {
    var matrix: Array<Array<Float>> = m

    fun get(row: Int, column: Int): Float {

        return matrix[row][column]
    }

    fun matmul(matrix1: Matrix): Matrix {
        val matrixOut = Array(size = matrix1.rows, init = { Array(size = matrix[0].size, init = { 0f }) })


        if (matrix1.columns != matrix.size) {
            println("Columns of matrix 1 must match rows of current matrix")
            return Matrix(emptyArray())
        }

        for (i in 0..matrix1.rows) {
            for (j in matrix[0].indices) {
                for (k in 0..matrix1.columns) {
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
}