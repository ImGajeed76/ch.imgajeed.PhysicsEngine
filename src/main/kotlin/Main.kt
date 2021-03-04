const val SCREEN_WIDTH: Float = 1024f
const val SCREEN_HEIGHT: Float = 768f

fun main() {
    val point3d = Matrix(arrayOf(
        arrayOf(50f, 30f, 10f),
        arrayOf(50f, 40f, 30f),
        arrayOf(50f, 100f, 150f)
    ))

    print(point3d.toString)
}