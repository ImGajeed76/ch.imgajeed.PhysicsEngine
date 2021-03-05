const val SCREEN_WIDTH: Int = 1024
const val SCREEN_HEIGHT: Int = 768
val frame: Window = Window(SCREEN_WIDTH, SCREEN_HEIGHT, "PhysicsEngine")
var angle = 0f

fun main() {
    val c = Cube(100f)
    c.draw()


    while (true) {
        c.rotateTo(angle, angle, angle)
        frame.update()

        Thread.sleep(10)

        angle += 0.005f
    }
}
