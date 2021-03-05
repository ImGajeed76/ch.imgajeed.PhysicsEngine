class Cube {
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

    val points3d: ArrayList<Matrix>
        get() {
            val halfWidth = width / 2
            val halfHeight = height / 2
            val halfDepth = depth / 2

            return arrayListOf(
                Matrix(
                    arrayListOf(
                        arrayListOf(x - halfWidth),
                        arrayListOf(y - halfHeight),
                        arrayListOf(z + halfDepth)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x - halfWidth),
                        arrayListOf(y - halfHeight),
                        arrayListOf(z - halfDepth)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x - halfWidth),
                        arrayListOf(y + halfHeight),
                        arrayListOf(z - halfDepth)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x - halfWidth),
                        arrayListOf(y + halfHeight),
                        arrayListOf(z + halfDepth)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x + halfWidth),
                        arrayListOf(y - halfHeight),
                        arrayListOf(z + halfDepth)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x + halfWidth),
                        arrayListOf(y - halfHeight),
                        arrayListOf(z - halfDepth)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x + halfWidth),
                        arrayListOf(y + halfHeight),
                        arrayListOf(z - halfDepth)
                    )
                ),
                Matrix(
                    arrayListOf(
                        arrayListOf(x + halfWidth),
                        arrayListOf(y + halfHeight),
                        arrayListOf(z + halfDepth)
                    )
                ),
            )
        }

    val points2d: ArrayList<Matrix>
        get() {
            val points2d: ArrayList<Matrix> = arrayListOf()
            val points3d = points3d

            for (i in 0 until points3d.size) {
                points2d.add(worldToScreenPoint(rotX, rotZ, rotY, points3d[i]))
            }

            return points2d
        }

    fun rotateTo(rotX: Float, rotY: Float, rotZ: Float) {
        this.rotX = rotX.toDouble()
        this.rotY = rotY.toDouble()
        this.rotZ = rotZ.toDouble()
        draw()
    }

    fun draw() {
        val points2d = points2d

        for (point2d in points2d) {
            frame.drawDot(point2d, 4f)
        }

        for (i in 0 until 4) {
            frame.drawLine(points2d[i], points2d[(i + 1) % 4])
            frame.drawLine(points2d[i + 4], points2d[((i + 1) % 4) + 4])
            frame.drawLine(points2d[i], points2d[i + 4])
        }
    }
}