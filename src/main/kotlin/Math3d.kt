import kotlin.math.cos
import kotlin.math.sin

fun worldToScreenPoint(rotX: Double, rotY: Double, rotZ: Double, pos: Matrix): Matrix {
    val hWidth: Float = SCREEN_WIDTH / 2
    val hHeight: Float = SCREEN_HEIGHT / 2

    val rotationX = Matrix(
        arrayOf(
            arrayOf(1f, 0f, 0f).toCollection(ArrayList()),
            arrayOf(0f, cos(rotX).toFloat(), -sin(rotX).toFloat()).toCollection(ArrayList()),
            arrayOf(0f, sin(rotX).toFloat(), cos(rotX).toFloat()).toCollection(ArrayList())
        ).toCollection(ArrayList())
    )

    val rotationY = Matrix(
        arrayOf(
            arrayOf(cos(rotY).toFloat(), 0f, sin(rotY).toFloat()).toCollection(ArrayList()),
            arrayOf(0f, 1f, 0f).toCollection(ArrayList()),
            arrayOf(-sin(rotY).toFloat(), 0f, cos(rotY).toFloat()).toCollection(ArrayList())
        ).toCollection(ArrayList())
    )

    val rotationZ = Matrix(
        arrayOf(
            arrayOf(cos(rotZ).toFloat(), -sin(rotZ).toFloat(), 0f).toCollection(ArrayList()),
            arrayOf(sin(rotZ).toFloat(), cos(rotZ).toFloat(), 0f).toCollection(ArrayList()),
            arrayOf(0f, 0f, 1f).toCollection(ArrayList())
        ).toCollection(ArrayList())
    )

    var rotated = pos.matmul(rotationX)
    rotated = rotated.matmul(rotationY)
    rotated = rotated.matmul(rotationZ)

    val projection = Matrix(
        arrayOf(
            arrayOf(1f, 0f, 0f).toCollection(ArrayList()),
            arrayOf(0f, 1f, 0f).toCollection(ArrayList()),
            arrayOf(0f, 0f, 0f).toCollection(ArrayList())
        ).toCollection(ArrayList())
    )

    val point2d = rotated.matmul(projection)

    point2d.add(0, 0, hWidth)
    point2d.add(1, 0, hHeight)

    return point2d
}
