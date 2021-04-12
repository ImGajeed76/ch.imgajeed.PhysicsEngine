import java.awt.Robot
import java.awt.event.*
import javax.swing.JFrame
import kotlin.math.cos
import kotlin.math.sin


class Camera {
    private val robot = Robot()
    private var catchMouse = false

    var locX: Float = 0f
    var locY: Float = 0f
    var locZ: Float = 0f

    var rotX: Float = 0f //In Radians (deg * pi / 180 = rad)
    var rotY: Float = 0f //*
    var rotZ: Float = 0f //*

    fun addFrame(frame: JFrame): JFrame {

        frame.addMouseMotionListener(object : MouseMotionListener {
            override fun mouseDragged(e: MouseEvent) {
                catchMouse = true
                moveMouse(e)
            }

            override fun mouseMoved(e: MouseEvent) {
                moveMouse(e)
            }
        })

        frame.addKeyListener(object : KeyListener {
            override fun keyPressed(e: KeyEvent) {
                val keyCode = e.keyCode
                catchMouse = true

                if (keyCode == KeyEvent.VK_W) {
                    cam.locX -= SPEED * sin(-cam.rotY)
                    cam.locZ -= SPEED * -cos(-cam.rotY)
                    cam.locY -= SPEED * sin(-cam.rotX)
                } else if (keyCode == KeyEvent.VK_S) {
                    cam.locX += SPEED * sin(-cam.rotY)
                    cam.locZ += SPEED * -cos(-cam.rotY)
                    cam.locY += SPEED * sin(-cam.rotX)
                } else if (keyCode == KeyEvent.VK_A) {
                    cam.locZ += SPEED * sin(cam.rotY)
                    cam.locX += SPEED * -cos(cam.rotY)
                } else if (keyCode == KeyEvent.VK_D) {
                    cam.locZ -= SPEED * sin(cam.rotY)
                    cam.locX -= SPEED * -cos(cam.rotY)
                } else if (keyCode == KeyEvent.VK_SPACE) {
                    cam.locY -= SPEED
                } else if (keyCode == KeyEvent.VK_SHIFT) {
                    cam.locY += SPEED
                } else if (keyCode == KeyEvent.VK_ESCAPE) {
                    catchMouse = false
                }
            }

            override fun keyReleased(e: KeyEvent) {
            }

            override fun keyTyped(e: KeyEvent) {

            }
        })

        return frame
    }

    fun moveMouse(e: MouseEvent) {
        if (catchMouse) {
            val x = (e.x - (SCREEN_WIDTH / 2))
            val y = (e.y - (SCREEN_HEIGHT / 2))
            cam.rotY += x.toFloat() / SCREEN_HEIGHT * 2
            cam.rotX += y.toFloat() / SCREEN_HEIGHT * 2
            robot.mouseMove(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2)
        }
    }
}