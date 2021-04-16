import kotlin.math.cos
import kotlin.math.sin

fun worldToScreenPoint(
    rotX: Float,
    rotY: Float,
    rotZ: Float,
    cam: Camera,
    pos: Matrix<Float>,
    x: Float,
    y: Float,
    z: Float
): Matrix<Float> {
    val hWidth: Int = SCREEN_WIDTH / 2
    val hHeight: Int = SCREEN_HEIGHT / 2

    val transformation = Matrix(
        arrayListOf(1f, 0f, 0f, -cam.locX),
        arrayListOf(0f, 1f, 0f, -cam.locY),
        arrayListOf(0f, 0f, 1f, -cam.locZ),
        arrayListOf(0f, 0f, 0f, 1f)

    )


    val backTrans = Matrix(
        arrayListOf(1f, 0f, 0f, -x),
        arrayListOf(0f, 1f, 0f, -y),
        arrayListOf(0f, 0f, 1f, -z),
        arrayListOf(0f, 0f, 0f, 1f)

    )

    val forTrans = Matrix(
        arrayListOf(1f, 0f, 0f, x),
        arrayListOf(0f, 1f, 0f, y),
        arrayListOf(0f, 0f, 1f, z),
        arrayListOf(0f, 0f, 0f, 1f)

    )

    val rotationX = Matrix(
        arrayListOf(1f, 0f, 0f, 0f),
        arrayListOf(0f, cos(rotX), -sin(rotX), 0f),
        arrayListOf(0f, sin(rotX), cos(rotX), 0f),
        arrayListOf(0f, 0f, 0f, 1f)

    )

    val rotationY = Matrix(
        arrayListOf(cos(rotY), 0f, sin(rotY), 0f),
        arrayListOf(0f, 1f, 0f, 0f),
        arrayListOf(-sin(rotY), 0f, cos(rotY), 0f),
        arrayListOf(0f, 0f, 0f, 1f)

    )

    val rotationZ = Matrix(
        arrayListOf(cos(rotZ), -sin(rotZ), 0f, 0f),
        arrayListOf(sin(rotZ), cos(rotZ), 0f, 0f),
        arrayListOf(0f, 0f, 1f, 0f),
        arrayListOf(0f, 0f, 0f, 1f)
    )

    val camRotX = Matrix(
        arrayListOf(1f, 0f, 0f, 0f),
        arrayListOf(0f, -cos(cam.rotX), sin(cam.rotX), 0f),
        arrayListOf(0f, -sin(cam.rotX), -cos(cam.rotX), 0f),
        arrayListOf(0f, 0f, 0f, 1f)
    )

    val camRotY = Matrix(
        arrayListOf(-cos(cam.rotY), 0f, -sin(cam.rotY), 0f),
        arrayListOf(0f, 1f, 0f, 0f),
        arrayListOf(sin(cam.rotY), 0f, -cos(cam.rotY), 0f),
        arrayListOf(0f, 0f, 0f, 1f)
    )

    val camRotZ = Matrix(
        arrayListOf(-cos(cam.rotZ), sin(cam.rotZ), 0f, 0f),
        arrayListOf(-sin(cam.rotZ), -cos(cam.rotZ), 0f, 0f),
        arrayListOf(0f, 0f, 1f, 0f),
        arrayListOf(0f, 0f, 0f, 1f)
    )


    val bTrans = pos.multiply(backTrans)

    var rotated = bTrans.multiply(rotationZ)
    rotated = rotated.multiply(rotationY)
    rotated = rotated.multiply(rotationX)

    val fTrans = rotated.multiply(forTrans)

    val trans = fTrans.multiply(transformation)

    var camRot = trans.multiply(camRotZ)
    camRot = camRot.multiply(camRotY)
    camRot = camRot.multiply(camRotX)

    val newZ = camRot[2][0] / 1000

    val projection = Matrix(

        arrayListOf(1f / newZ, 0f, 0f, 0f),
        arrayListOf(0f, 1f / newZ, 0f, 0f),
        arrayListOf(0f, 0f, 1f, 0f),
        arrayListOf(0f, 0f, 0f, 1f)

    )

    val point2d = camRot.multiply(projection)

    point2d[0, 0] += hWidth.toFloat()
    point2d[1, 0] += hHeight.toFloat()

    if (point2d[2][0] < 0) {
        return Matrix(
            arrayListOf(-1f),
            arrayListOf(-1f),
            arrayListOf(0f),
            arrayListOf(1f)
        )
    }

    return point2d

}
