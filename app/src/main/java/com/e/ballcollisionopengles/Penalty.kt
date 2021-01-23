package com.e.ballcollisionopengles

import kotlin.math.*

class Penalty(var px1:Float ,var py1:Float,var px2:Float ,var py2:Float
              ,val r1:Float ,var pos:Float, var touch:Int){

    // "penalty" wrong name?  Release the overlapping circles
    fun penalty() {
        if (r1 + r1 >= sqrt((px1 - px2) * (px1 - px2) + (py1 - py2) * (py1 - py2))) {
            touch = 1
            val d = sqrt((px1 - px2) * (px1 - px2) + (py1 - py2) * (py1 - py2))
            val D = r1 + r1 - d
            val dx1 = px1 - px2
            val dy1 = py1 - py2
            if (px1 == px2 && py1 > py2) {
                pos = 90.0f
            } else if (px1 == px2 && py1 < py2) {
                pos = 270.0f
            } else if (py1 == py2 && px1 > px2) {
                pos = 0.0f
            } else if (py1 == py2 && px1 < px2) {
                pos = 180.0f
            } else {
                pos = (atan2(dy1, dx1)) * 180f / PI.toFloat()
            }
            val dx = D * cos(pos * PI / 180f)
            val dy = D * sin(pos * PI / 180f)
            px1 = px1 + (dx.toFloat() / 2)
            px2 = px2 - (dx.toFloat() / 2)
            py1 = py1 + (dy.toFloat() / 2)
            py2 = py2 - (dy.toFloat() / 2)
        } else {
            touch = 0
        }
    }
}
