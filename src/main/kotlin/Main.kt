import javax.swing.JFrame

var SCREEN_WIDTH: Int = 1000
var SCREEN_HEIGHT: Int = 700
val cam: Camera = Camera()
val frame: Window = Window(SCREEN_WIDTH, SCREEN_HEIGHT, "PhysicsEngine", cam.addFrame(JFrame()))
var angle = 0f
const val SPEED: Float = 5f

fun main() {
    val c1 = Cube(100f, 100f, 100f, 0f, 0f, 300f)
    val c2 = Cube(100f, 100f, 100f, 0f, 0f, -300f)
    val c3 = Cube(100f, 100f, 100f, 300f, 0f, 0f)
    val c4 = Cube(100f, 100f, 100f, -300f, 0f, 0f)

    frame.shapes.add(c1)
    frame.shapes.add(c2)
    frame.shapes.add(c3)
    frame.shapes.add(c4)
    Thread(frame).start()


    while (true) {
        frame.update()
        angle += 0.0001f
    }
}

