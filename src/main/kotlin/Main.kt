const val SCREEN_WIDTH: Float = 1024f
const val SCREEN_HEIGHT: Float = 768f

fun main() {
    var frame = Window(500, 500, "Test")
    frame.init()
    frame.drawLine(100f, 100f, 200f, 200f)
    frame.update()
}