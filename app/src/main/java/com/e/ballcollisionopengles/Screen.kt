package com.e.ballcollisionopengles

class Screen (var px1:Float,var py1:Float, var vx1:Float,var vy1:Float
               ,val r1:Float, val g:Float){

    //Movement in the screen ,resistance reflection and gravity
    fun screen() {
        //減速、抵抗　speed down
        val resistance = 0.999f
        vx1 = resistance * vx1
        px1 = px1 + vx1
        vy1 = resistance * vy1 - g
        py1 = py1 + vy1
        //反発係数　reflection
        val reflection = 0.99f
        if (px1 <= -3.3f + r1) {
            px1 = -3.3f + r1
            vx1 = -reflection * vx1
            px1 = px1 + vx1
        } else if (3.3f - r1 <= px1) {
            px1 = 3.3f - r1
            vx1 = -reflection * vx1
            px1 = px1 + vx1
        }
        if (py1 <= -5.0f + r1) {
            py1 = -5.0f + r1
            vy1 = -reflection * vy1
            py1 = py1 + vy1
        } else if (5.0f - r1 <= py1) {
            py1 = 5.0f - r1
            vy1 = -reflection * vy1
            py1 = py1 + vy1
        }
    }
}