import javax.swing.JFrame

var SCREEN_WIDTH: Int = 1000
var SCREEN_HEIGHT: Int = 700
val cam: Camera = Camera()
val frame: Window = Window(SCREEN_WIDTH, SCREEN_HEIGHT, "PhysicsEngine", cam.addFrame(JFrame()))
var angle = 0f
const val SPEED: Float = 5f

fun main() {
    val c1 = Cube(100f, 100f, 100f, 0f, 0f, 300f)
    frame.shapes.add(c1)

    Thread(frame).start()
    frame.run()
}

