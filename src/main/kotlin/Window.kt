import java.awt.Graphics
import java.awt.GraphicsDevice
import java.awt.MouseInfo
import javax.swing.JFrame
import javax.swing.JPanel


class Window(width: Int, height: Int, title: String, f: JFrame = JFrame()) : Runnable {

    private var frame = JFrame()
    private var panel = Panel2(this)

    var gd: GraphicsDevice = MouseInfo.getPointerInfo().device

    private var lines: Matrix<Float> = Matrix(arrayListOf())
    private var dots: Matrix<Float> = Matrix(arrayListOf())

    val shapes: ArrayList<Shape> = arrayListOf()

    init {
        frame = f
        frame.title = title
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setBounds(0, 0, width, height)
        frame.add(panel)
        frame.isResizable = false
        frame.isVisible = true
        gd = frame.graphicsConfiguration.device
        SCREEN_WIDTH = gd.displayMode.width + 16
        SCREEN_HEIGHT = gd.displayMode.height + 9
    }

    fun update() {
        gd = frame.graphicsConfiguration.device
        SCREEN_WIDTH = gd.displayMode.width + 8
        SCREEN_HEIGHT = gd.displayMode.height + 8
        frame.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT)
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
            gd = frame.graphicsConfiguration.device
            SCREEN_WIDTH = gd.displayMode.width + 8
            SCREEN_HEIGHT = gd.displayMode.height + 8
            frame.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT)
            panel = Panel2(this)
            frame.invalidate()
            frame.validate()
            frame.repaint()
        }
    }

    class Panel2(window: Window) : JPanel() {
        private val frame = window

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            for (shape in frame.shapes) {
                shape.draw(g)
            }
        }
    }
}