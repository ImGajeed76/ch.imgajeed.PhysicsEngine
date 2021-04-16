class Matrix<T> {
    private var array2d: ArrayList<ArrayList<T>> = arrayListOf()

    constructor(vararg elements: ArrayList<T>){
        array2d = elements.toCollection(ArrayList())
    }

    constructor(elements: ArrayList<ArrayList<T>>){
        array2d = elements
    }

    fun multiply(matrix: Matrix<Float>): Matrix<Float> {
        try {
            val mOut: ArrayList<ArrayList<Float>> = arrayListOf()

            if (matrix.columns != array2d.size){
                error("Columns of multiplied matrix must match rows of current matrix.")
            }

            for (i in 0 until matrix.rows){
                mOut.add(arrayListOf())
                for (j in array2d[0].indices){
                    mOut[i].add(0f)
                    for (k in 0 until matrix.columns){
                        mOut[i][j] = mOut[i][j] + matrix[i ,k] * array2d[k][j] as Float
                    }
                }
            }

            return Matrix(mOut)

        } catch (e: ArithmeticException) {
            error("Can not multiply matrices. Not a number.")
        }
    }

    fun toFloat(): Matrix<Float> {
        var m = Matrix<Float>()

        for (row in 0 until array2d.size){
            m.applyRow(arrayListOf())
            for (column in 0 until array2d[0].size){
                m.applyColumn(row, array2d[row][column] as Float)
            }
        }

        return m
    }

    override fun toString(): String {
        var string: String = ""
        for (row in array2d) {
            string += "["
            for (column in row) {
                string += "$column, "
            }
            string = string.slice(0..string.length - 3)
            string += "] \n"
        }
        return string
    }

    val rows: Int
        get() {
            return array2d.size
        }

    val columns: Int
        get() {
            return array2d[0].size
        }

    fun applyRow(element: ArrayList<T>) {
        array2d.add(element)
    }

    fun applyColumn(row: Int, element: T) {
        array2d[row].add(element)
    }

    operator fun get(row: Int): ArrayList<T> {
        return array2d[row]
    }

    operator fun get(row: Int, column: Int): T {
        return array2d[row][column]
    }

    operator fun set(row: Int, column: Int, element: T){
        array2d[row][column] = element
    }

}