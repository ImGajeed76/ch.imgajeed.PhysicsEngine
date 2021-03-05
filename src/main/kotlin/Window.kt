import java.awt.Dimension
import java.awt.Graphics
import java.awt.Shape
import javax.swing.*

class Window(width: Int, height: Int, title: String) {
    val width = width
    val height = height
    val title = title

    var frame = JFrame()
    var panel = Panel2(this)

    var lines: Matrix = Matrix(arrayListOf())
    var dots: Matrix = Matrix(arrayListOf())

    init {
        frame.title = title
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(width, height)
        frame.add(panel)
        frame.isVisible = true
    }

    fun update() {
        panel = Panel2(this)
        frame.invalidate()
        frame.validate()
        frame.repaint()
    }

    fun drawLine(x1: Float, y1: Float, x2: Float, y2: Float) {
        lines.apply(arrayListOf(x1, y1, x2, y2))
    }

    fun drawLine(p1: Matrix, p2: Matrix) {
        lines.apply(arrayListOf(p1[0, 0], p1[1, 0], p2[0, 0], p2[1, 0]))
    }

    fun drawTriangle(p1: Matrix, p2: Matrix, p3: Matrix) {
        drawLine(p1, p2)
        drawLine(p2, p3)
        drawLine(p3, p1)
    }

    fun drawDot(x: Float, y: Float, size: Float = 4f) {
        dots.apply(arrayListOf(x - size / 2, y - size / 2, size))
    }

    fun drawDot(p: Matrix, size: Float) {
        dots.apply(arrayListOf(p[0][0] - (size / 2), p[1][0] - (size / 2), size))
    }

    class Panel2(window: Window) : JPanel() {
        val frame = window

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)

            for (line in this.frame.lines.matrix) {
                g.drawLine(line[0].toInt(), line[1].toInt(), line[2].toInt(), line[3].toInt())
            }

            for (dot in this.frame.dots.matrix) {
                g.fillRoundRect(dot[0].toInt(), dot[1].toInt(), dot[2].toInt(), dot[2].toInt(), 0, 0)
            }

            this.frame.lines = Matrix(arrayListOf())
            this.frame.dots = Matrix(arrayListOf())
        }
    }
}