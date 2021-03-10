import org.imgajeed.matrix.Matrix
import kotlin.math.cos
import kotlin.math.sin

fun worldToScreenPoint(cam: Camera, pos: Matrix<Float>): Matrix<Float> {
    val hWidth: Int = SCREEN_WIDTH / 2
    val hHeight: Int = SCREEN_HEIGHT / 2

    val transformation = Matrix(
            arrayListOf(1f, 0f, 0f, -cam.locX),
            arrayListOf(0f, 1f, 0f, -cam.locY),
            arrayListOf(0f, 0f, 1f, -cam.locZ),
            arrayListOf(0f, 0f, 0f, 1f)

    )

    val rotationX = Matrix(
            arrayListOf(1f, 0f, 0f, 0f),
            arrayListOf(0f, cos(cam.rotX), -sin(cam.rotX), 0f),
            arrayListOf(0f, sin(cam.rotX), cos(cam.rotX), 0f),
            arrayListOf(0f, 0f, 0f, 1f)

    )

    val rotationY = Matrix(
            arrayListOf(cos(cam.rotY), 0f, sin(cam.rotY), 0f),
            arrayListOf(0f, 1f, 0f, 0f),
            arrayListOf(-sin(cam.rotY), 0f, cos(cam.rotY), 0f),
            arrayListOf(0f, 0f, 0f, 1f)

    )

    val rotationZ = Matrix(
            arrayListOf(cos(cam.rotZ), -sin(cam.rotZ), 0f, 0f),
            arrayListOf(sin(cam.rotZ), cos(cam.rotZ), 0f, 0f),
            arrayListOf(0f, 0f, 1f, 0f),
            arrayListOf(0f, 0f, 0f, 1f)

    )

    var trans = pos.multiply(transformation)

    var rotated = trans.multiply(rotationZ)
    rotated = rotated.multiply(rotationY)
    rotated = rotated.multiply(rotationX)

    val projection = Matrix(

            arrayListOf(1f, 0f, 0f, 0f),
            arrayListOf(0f, 1f, 0f, 0f),
            arrayListOf(0f, 0f, 1f, 0f),
            arrayListOf(0f, 0f, 0f, 1f)

    )

    val point2d = rotated.multiply(projection)

    point2d[0, 0] += hWidth.toFloat()
    point2d[1, 0] += hHeight.toFloat()

    return point2d
}
