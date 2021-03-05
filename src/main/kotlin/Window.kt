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

    fun init() {
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
        print(lines.toString)
        update()
    }

    class Panel2(window: Window) : JPanel() {
        val frame = window

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            for (line in this.frame.lines.matrix) {
                g.drawLine(line[0].toInt(), line[1].toInt(), line[2].toInt(), line[3].toInt())
            }
        }
    }
}