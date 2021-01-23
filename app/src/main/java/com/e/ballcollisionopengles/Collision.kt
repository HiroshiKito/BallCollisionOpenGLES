package com.e.ballcollisionopengles

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Collision  (var vx1:Float ,var vy1:Float ,var vx2:Float ,var vy2:Float ,var pos:Float, var touch:Int){
    // This uses a rotation matrix
    // Wikipedia "Elastic collision"

    fun collision() {

        // reflection
        if (touch == 1) {

            //position : balls center position angle
            val position = pos

            //　tilt : ball's tangent normal
            var tilt = position + 90
            //z axis　rotation matrix　
            val vx10 = vx1 * cos(-tilt * PI / 180f) - vy1 * sin(-tilt * PI / 180f)
            val vy10 = vx1 * sin(-tilt * PI / 180f) + vy1 * cos(-tilt * PI / 180f)
            val vx20 = vx2 * cos(-tilt * PI / 180f) - vy2 * sin(-tilt * PI / 180f)
            val vy20 = vx2 * sin(-tilt * PI / 180f) + vy2 * cos(-tilt * PI / 180f)
            // y axis reflection
            val b = 0.98f
            val vy10r = b * vy20
            val vy20r = b * vy10
            // ball 1 Reflection , ball 2 incidence
            val vx1r = vx10 * cos(tilt * PI / 180f) - vy10r * sin(tilt * PI / 180f)
            val vy1r = vx10 * sin(tilt * PI / 180f) + vy10r * cos(tilt * PI / 180f)
            val vx2i = vx20 * cos(tilt * PI / 180f) - vy20r * sin(tilt * PI / 180f)
            val vy2i = vx20 * sin(tilt * PI / 180f) + vy20r * cos(tilt * PI / 180f)

            vx1 = vx1r.toFloat()
            vy1 = vy1r.toFloat()
            vx2 = vx2i.toFloat()
            vy2 = vy2i.toFloat()
            touch = 0

        }
    }
}
