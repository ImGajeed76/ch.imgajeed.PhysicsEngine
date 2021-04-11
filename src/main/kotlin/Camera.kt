class Camera {
    var locX: Float = 0f
    var locY: Float = 0f
    var locZ: Float = 0f

    var rotX: Float = 0f //In Radians (deg * pi / 180 = rad)
    var rotY: Float = 0f //*
    var rotZ: Float = 0f //*

    fun rotateTo(x: Float, y:Float, z:Float){
        rotX = x
        rotY = y
        rotZ = z
    }
}