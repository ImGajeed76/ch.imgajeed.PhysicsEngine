import org.imgajeed.matrix.Matrix
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Robot
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.math.cos
import kotlin.math.sin


class Window(width: Int, height: Int, title: String) : Runnable {
    val width = width
    val height = height
    val title = title

    val robot = Robot()
    var catchMouse = false

    var frame = JFrame()
    var panel = Panel2(this)

    var lines: Matrix<Float> = Matrix(arrayListOf())
    var dots: Matrix<Float> = Matrix(arrayListOf())

    val shapes: ArrayList<Shape> = arrayListOf()

    init {
        frame.title = title
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(width, height)
        frame.add(panel)
        frame.isVisible = true

        frame.addMouseMotionListener(object : MouseMotionListener {
            override fun mouseDragged(e: MouseEvent) {
                catchMouse = true
                if (catchMouse) {
                    val x = (e.x - (SCREEN_WIDTH / 2))
                    val y = (e.y - (SCREEN_HEIGHT / 2))
                    cam.rotY += x.toFloat() / 1000
                    cam.rotX += y.toFloat() / 1000
                    robot.mouseMove(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2)
                }
            }

            override fun mouseMoved(e: MouseEvent) {
                if (catchMouse) {
                    val x = (e.x - (SCREEN_WIDTH / 2))
                    val y = (e.y - (SCREEN_HEIGHT / 2))
                    cam.rotY += x.toFloat() / 1000
                    cam.rotX += y.toFloat() / 1000
                    robot.mouseMove(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2)
                }
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
    }

    fun update() {
        panel = Panel2(this)
        frame.invalidate()
        frame.validate()
        frame.repaint()
    }

    fun drawLine(x1: Float, y1: Float, x2: Float, y2: Float) {
        lines.applyRow(arrayListOf(x1, y1, x2, y2))
    }

    fun drawLine(p1: Matrix<Float>, p2: Matrix<Float>) {
        lines.applyRow(arrayListOf(p1[0, 0], p1[1, 0], p2[0, 0], p2[1, 0]))
    }

    fun drawTriangle(p1: Matrix<Float>, p2: Matrix<Float>, p3: Matrix<Float>) {
        drawLine(p1, p2)
        drawLine(p2, p3)
        drawLine(p3, p1)
    }

    fun drawDot(x: Float, y: Float, size: Float = 4f) {
        dots.applyRow(arrayListOf(x - size / 2, y - size / 2, size))
    }

    fun drawDot(p: Matrix<Float>, size: Float) {
        dots.applyRow(arrayListOf(p[0][0] - (size / 2), p[1][0] - (size / 2), size))
    }

    override fun run() {
        while (true) {
            this.panel = Panel2(this)
            this.frame.invalidate()
            this.frame.validate()
            this.frame.repaint()
        }
    }

    class Panel2(window: Window) : JPanel() {
        val frame = window

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            for (shape in frame.shapes) {
                shape.draw(g)
            }

//            for (line in this.frame.lines.matrix) {
//                g.drawLine(line[0].toInt(), line[1].toInt(), line[2].toInt(), line[3].toInt())
//            }
//
//            for (dot in this.frame.dots.matrix) {
//                g.fillRoundRect(dot[0].toInt(), dot[1].toInt(), dot[2].toInt(), dot[2].toInt(), 0, 0)
//            }
//
//            this.frame.lines = Matrix(arrayListOf())
//            this.frame.dots = Matrix(arrayListOf())
        }
    }
}