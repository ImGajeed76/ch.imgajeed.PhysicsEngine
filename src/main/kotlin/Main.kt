const val SCREEN_WIDTH: Float = 1024f
const val SCREEN_HEIGHT: Float = 768f

fun main() {
    val point3d: Array<Array<Float>> = arrayOf(
        arrayOf(50f),
        arrayOf(50f),
        arrayOf(50f)
    )

    val point2d = worldToScreenPoint(0.0, 0.0, 0.0, point3d)
    print(point2d)
}