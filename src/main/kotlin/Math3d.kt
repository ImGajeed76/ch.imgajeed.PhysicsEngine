import kotlin.math.cos
import kotlin.math.sin

fun worldToScreenPoint(rotX: Double, rotY: Double, rotZ: Double, pos: Array<Array<Float>>): Array<Array<Float>> {
    val hWidth = SCREEN_WIDTH / 2
    val hHeight = SCREEN_HEIGHT / 2

    val rotationX: Array<Array<Float>> = arrayOf(
        arrayOf(1f, 0f, 0f),
        arrayOf(0f, cos(rotX).toFloat(), -sin(rotX).toFloat()),
        arrayOf(0f, sin(rotX).toFloat(), cos(rotX).toFloat())
    )

    val rotationY: Array<Array<Float>> = arrayOf(
        arrayOf(cos(rotY).toFloat(), 0f, sin(rotY).toFloat()),
        arrayOf(0f, 1f, 0f),
        arrayOf(-sin(rotY).toFloat(), 0f, cos(rotY).toFloat())
    )

    val rotationZ: Array<Array<Float>> = arrayOf(
        arrayOf(cos(rotZ).toFloat(), -sin(rotZ).toFloat(), 0f),
        arrayOf(sin(rotZ).toFloat(), cos(rotZ).toFloat(), 0f),
        arrayOf(0f, 0f, 1f)
    )

    var rotated = matmul(rotationX, pos)
    rotated = matmul(rotationY, rotated)
    rotated = matmul(rotationZ, rotated)

    val projection: Array<Array<Float>> = arrayOf(
        arrayOf(1f, 0f, 0f),
        arrayOf(0f, 1f, 0f),
        arrayOf(0f, 0f, 0f)
    )

    val point2d = matmul(projection, rotated)

    point2d[0][0] += hWidth
    point2d[1][0] += hHeight

    return point2d
}

fun matmul(matrix1: Array<Array<Float>>, matrix2: Array<Array<Float>>): Array<Array<Float>> {
    val matrixOut : Array<Array<Float>> = emptyArray()
    for (i in 0..matrix1.size){
        matrixOut.apply { FloatArray(matrix2[0].size) }
    }

    if (matrix1[0].size != matrix2.size) {
        println("Columns of matrix 1 must match rows of matrix 2")
        return emptyArray()
    }

    for (i in 0..matrix1.size) {
        for (j in 0..matrix2[0].size) {
            for (k in 0..matrix1[0].size) {
                matrixOut[i][j] += matrix1[i][k] * matrix2[k][j]
            }
        }
    }

    return matrixOut
}