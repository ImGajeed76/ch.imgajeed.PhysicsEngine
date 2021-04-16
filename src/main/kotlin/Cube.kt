import java.awt.Graphics

class Cube : Shape {
    var width = 0f
    var height = 0f
    var depth = 0f
    var rotX = 0.0
    var rotY = 0.0
    var rotZ = 0.0
    var x = 0f
    var y = 0f
    var z = 0f


    constructor(side: Float) : this(side, side, side)
    constructor(width: Float, height: Float, depth: Float) : this(width, height, depth, 0f, 0f, 0f)
    constructor(width: Float, height: Float, depth: Float, x: Float, y: Float, z: Float) :
            this(width, height, depth, x, y, z, 0f, 0f, 0f)

    constructor(
        width: Float,
        height: Float,
        depth: Float,
        x: Float,
        y: Float,
        z: Float,
        rotX: Float,
        rotY: Float,
        rotZ: Float
    ) {
        this.width = width
        this.height = height
        this.depth = depth
        this.rotX = rotX.toDouble()
        this.rotY = rotY.toDouble()
        this.rotZ = rotZ.toDouble()
        this.x = x
        this.y = y
        this.z = z
    }

    val points3d: ArrayList<Matrix<Float>>
        get() {
            val halfWidth = width / 2
            val halfHeight = height / 2
            val halfDepth = depth / 2

            return arrayListOf(
                Matrix(
                    arrayListOf(
                        arrayListOf(x - halfWidth),
                        arrayListOf(y - halfHeight),
                        arrayListOf(z + halfDepth),
                        arrayListOf(1f)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x - halfWidth),
                        arrayListOf(y - halfHeight),
                        arrayListOf(z - halfDepth),
                        arrayListOf(1f)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x - halfWidth),
                        arrayListOf(y + halfHeight),
                        arrayListOf(z - halfDepth),
                        arrayListOf(1f)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x - halfWidth),
                        arrayListOf(y + halfHeight),
                        arrayListOf(z + halfDepth),
                        arrayListOf(1f)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x + halfWidth),
                        arrayListOf(y - halfHeight),
                        arrayListOf(z + halfDepth),
                        arrayListOf(1f)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x + halfWidth),
                        arrayListOf(y - halfHeight),
                        arrayListOf(z - halfDepth),
                        arrayListOf(1f)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x + halfWidth),
                        arrayListOf(y + halfHeight),
                        arrayListOf(z - halfDepth),
                        arrayListOf(1f)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x + halfWidth),
                        arrayListOf(y + halfHeight),
                        arrayListOf(z + halfDepth),
                        arrayListOf(1f)
                    )
                ),
            )
        }

    val points2d: ArrayList<Matrix<Float>>
        get() {
            val points2d: ArrayList<Matrix<Float>> = arrayListOf()
            val points3d = points3d

            for (i in 0 until points3d.size) {
                points2d.add(
                    worldToScreenPoint(
                        this.rotX.toFloat(),
                        this.rotY.toFloat(),
                        this.rotZ.toFloat(),
                        cam,
                        points3d[i],
                        x,
                        y,
                        z
                    )
                )
            }

            return points2d
        }

    fun rotateTo(rotX: Float, rotY: Float, rotZ: Float) {
        this.rotX = rotX.toDouble()
        this.rotY = rotY.toDouble()
        this.rotZ = rotZ.toDouble()
    }

    fun onDisplay(pos: Matrix<Float>): Boolean {
        return pos[0][0] > 0 && pos[0][0] < SCREEN_WIDTH &&
                pos[1][0] > 0 && pos[1][0] < SCREEN_HEIGHT
    }

    fun pointsOnDisplay(points: ArrayList<Matrix<Float>>): Boolean {
        var re = false

        for (point in points){
            if (onDisplay(point)){
                re = true
            }
        }

        return re
    }

    override fun draw(g: Graphics) {
        val pointsToDraw = points2d

        if (!pointsOnDisplay(pointsToDraw)){
            return
        }

        for (point2d in pointsToDraw) {
            g.drawRect(point2d[0][0].toInt() - 2, point2d[1][0].toInt() - 2, 4, 4)
            g.drawRect(point2d[0][0].toInt() - 1, point2d[1][0].toInt() - 1, 2, 2)
            g.drawRect(point2d[0][0].toInt(), point2d[1][0].toInt(), 1, 1)
        }

        for (i in 0 until 4) {
            g.drawLine(
                pointsToDraw[i][0][0].toInt(),
                pointsToDraw[i][1][0].toInt(),
                pointsToDraw[(i + 1) % 4][0][0].toInt(),
                pointsToDraw[(i + 1) % 4][1][0].toInt()
            )
            g.drawLine(
                pointsToDraw[i + 4][0][0].toInt(),
                pointsToDraw[i + 4][1][0].toInt(),
                pointsToDraw[((i + 1) % 4) + 4][0][0].toInt(),
                pointsToDraw[((i + 1) % 4) + 4][1][0].toInt()
            )
            g.drawLine(
                pointsToDraw[i][0][0].toInt(),
                pointsToDraw[i][1][0].toInt(),
                pointsToDraw[i + 4][0][0].toInt(),
                pointsToDraw[i + 4][1][0].toInt()
            )
        }

    }
}