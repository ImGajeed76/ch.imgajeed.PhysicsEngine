import org.imgajeed.matrix.Matrix

const val SCREEN_WIDTH: Int = 1024
const val SCREEN_HEIGHT: Int = 768
val frame: Window = Window(SCREEN_WIDTH, SCREEN_HEIGHT, "PhysicsEngine")
val cam: Camera = Camera()
var angle = 0f

fun main() {
    val c = Cube(100f)

    frame.shapes.add(c)
    Thread(frame).start()

    while (true) {
        c.rotateTo(angle, angle, angle)
//        frame.update()
//
//        Thread.sleep(10)

        angle += 0.005f
    }
}
