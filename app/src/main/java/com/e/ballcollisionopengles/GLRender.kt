package com.e.ballcollisionopengles

import android.opengl.GLSurfaceView
import android.opengl.GLU
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class GLRender : GLSurfaceView.Renderer {

    //2019にkotlinの勉強を始めたプログラミング経験浅い人なので変な書き方だと思いますが許して下さい
    //あまり理解せず書いてる部分もあります
    //変数名は英語ができないので怪しい部分が多いです
    //玉１と玉２の衝突を計算しています
    //玉１は Array の１番目ではありません

    //I'm a beginner who started programming in 2019
    //It may not be written correctly
    //I speak Japanese so I think the variable names are weird
    //ball 1 and ball 2 collision. 1 and 2 are not Array number



    private var lock:Int = 0

    private var touch :Int = 0
    private var touchn :Array<Int> = arrayOf()

    private val g :Float = 0.0001f        /////重力 gravity

    private var num :Int = 100           /////玉の数 bal number
    private var px1 :Float = 0.0f
    private var py1 :Float = 0.0f
    private var vx1 :Float = 0.0f
    private var vy1 :Float = 0.0f
    private val r1 :Float = 0.14f         /////玉１の半径 ball radius
    private var px1b :Float = 0.0f
    private var py1b :Float = 0.0f

    private var px2 :Float = 0.0f
    private var py2 :Float = 0.0f
    private var vx2 :Float = 0.0f
    private var vy2 :Float = 0.0f

    private var pxn :Array<Float> = arrayOf()
    private var pyn :Array<Float> = arrayOf()
    private var vxn :Array<Float> = arrayOf()
    private var vyn :Array<Float> = arrayOf()

    //1つ前のデータ
    //Data one frame before
    private var pxb :Array<Float> = arrayOf()
    private var pyb :Array<Float> = arrayOf()

    //玉１中心と玉２中心の位置関係(角度)
    //pos:balls center position angle
    private var pos :Float = 0.0f

////////////////////////////////////


    fun ballList(n:Int){
        if(lock==0) {
            for (i in 0..n) {
                if(i<=16) {
                    pxn += (-2.8f + r1 + i * (2 * r1 + 0.03f))
                    pxb += (-2.8f + r1 + i * (2 * r1 + 0.04f))
                    pyn += (4.5f) - 4f * r1
                    pyb += (4.5f) - 4f * r1
                    vxn += (0.01f)
                    vyn += (0.0f)
                    touchn += 0
                }else if(16<i && i<=32){
                    pxn += (-2.8f + r1 + (i-16) * (2 * r1 + 0.04f))
                    pxb += (-2.8f + r1 + (i-16) * (2 * r1 + 0.05f))
                    pyn += (4.5f) -5.5f*r1
                    pyb += (4.5f) -5.5f*r1
                    vxn += (0.0f)
                    vyn += (0.01f)
                    touchn += 0
                }else if(32<i && i<=48){
                    pxn += (-2.8f + r1 + (i-32) * (2 * r1 + 0.05f))
                    pxb += (-2.8f + r1 + (i-32) * (2 * r1 + 0.06f))
                    pyn += (4.5f) -7f*r1
                    pyb += (4.5f) -7f*r1
                    vxn += (0.02f)
                    vyn += (0.0f)
                    touchn += 0
                }else if(48<i && i<=64){
                    pxn += (-2.8f + r1 + (i-48) * (2 * r1 + 0.03f))
                    pxb += (-2.8f + r1 + (i-48) * (2 * r1 + 0.04f))
                    pyn += (4.5f) -8.5f*r1
                    pyb += (4.5f) -8.5f*r1
                    vxn += (0.0f)
                    vyn += (0.02f)
                    touchn += 0
                }else if(64<i && i<=80){
                    pxn += (-2.8f + r1 + (i-64) * (2 * r1 + 0.04f))
                    pxb += (-2.8f + r1 + (i-64) * (2 * r1 + 0.05f))
                    pyn += (4.5f) -10f*r1
                    pyb += (4.5f) -10f*r1
                    vxn += (0.0f)
                    vyn += (0.0f)
                    touchn += 0
                }else if(80<i && i<=96){
                    pxn += (-2.8f + r1 + (i-80) * (2 * r1 + 0.05f))
                    pxb += (-2.8f + r1 + (i-80) * (2 * r1 + 0.06f))
                    pyn += (4.5f) -11.5f*r1
                    pyb += (4.5f) -11.5f*r1
                    vxn += (0.03f)
                    vyn += (0.0f)
                    touchn += 0
                }else if(96<i && i<=112){
                    pxn += (-2.8f + r1 + (i-96) * (2 * r1 + 0.03f))
                    pxb += (-2.8f + r1 + (i-96) * (2 * r1 + 0.04f))
                    pyn += (4.5f) -13f*r1
                    pyb += (4.5f) -13f*r1
                    vxn += (0.0f)
                    vyn += (0.03f)
                    touchn += 0
                }else if(112<i && i<=128){
                    pxn += (-2.8f + r1 + (i-112) * (2 * r1 + 0.04f))
                    pxb += (-2.8f + r1 + (i-112) * (2 * r1 + 0.05f))
                    pyn += (4.5f) -14.5f*r1
                    pyb += (4.5f) -14.5f*r1
                    vxn += (0.04f)
                    vyn += (0.0f)
                    touchn += 0
                }else if(128<i && i<=144){
                    pxn += (-2.8f + r1 + (i-128) * (2 * r1 + 0.05f))
                    pxb += (-2.8f + r1 + (i-128) * (2 * r1 + 0.06f))
                    pyn += (4.5f) -16f*r1
                    pyb += (4.5f) -16f*r1
                    vxn += (0.0f)
                    vyn += (0.04f)
                    touchn += 0
                }else if(144<i && i<=160){
                    pxn += (-2.8f + r1 + (i-144) * (2 * r1 + 0.03f))
                    pxb += (-2.8f + r1 + (i-144) * (2 * r1 + 0.04f))
                    pyn += (4.5f) -17.5f*r1
                    pyb += (4.5f) -17.5f*r1
                    vxn += (0.0f)
                    vyn += (0.0f)
                    touchn += 0
                }else if(160<i && i<=176){
                    pxn += (-2.8f + r1 + (i-160) * (2 * r1 + 0.04f))
                    pxb += (-2.8f + r1 + (i-160) * (2 * r1 + 0.05f))
                    pyn += (4.5f) -18.5f*r1
                    pyb += (4.5f) -18.5f*r1
                    vxn += (0.0f)
                    vyn += (0.0f)
                    touchn += 0
                }else if(176<i && i<=192){
                    pxn += (-2.8f + r1 + (i-176) * (2 * r1 + 0.05f))
                    pxb += (-2.8f + r1 + (i-176) * (2 * r1 + 0.06f))
                    pyn += (4.5f) -19.5f*r1
                    pyb += (4.5f) -19.5f*r1
                    vxn += (0.0f)
                    vyn += (0.0f)
                    touchn += 0
                }else if(192<i && i<=208){
                    pxn += (-2.8f + r1 + (i-176) * (2 * r1 + 0.05f))
                    pxb += (-2.8f + r1 + (i-176) * (2 * r1 + 0.06f))
                    pyn += (4.5f) -19.5f*r1
                    pyb += (4.5f) -19.5f*r1
                    vxn += (0.0f)
                    vyn += (0.0f)
                    touchn += 0
                }
            }
            lock=1
        }
    }

    // It build ball 1 from Array
    fun buildBall(n:Int){
        px1 = pxn[n]
        py1 = pyn[n]
        vx1 = vxn[n]
        vy1 = vyn[n]
        touch = touchn[n]
        px1b = pxb[n]
        py1b = pyb[n]
    }

    // It build ball 2 from Array
    fun buildBall2(n:Int){
        px2 = pxn[n]
        py2 = pyn[n]
        vx2 = vxn[n]
        vy2 = vyn[n]
    }

    fun beforeBall(n:Int){
        for(i in 1..n){
            pxb.set(i,pxn[i])
            pyb.set(i,pyn[i])
        }
    }

    override fun onSurfaceCreated(gl10: GL10, eglc: EGLConfig) {
    }

    override fun onSurfaceChanged(gl10: GL10, w: Int, h: Int) {
        gl10.glViewport(0,0,w,h)
        val ratio = w.toFloat() / h
        gl10.glMatrixMode(GL10.GL_PROJECTION)
        gl10.glLoadIdentity()
        //カメラ、視点の設定
        //camera,point of view
        gl10.glFrustumf(-ratio, ratio, -1f, 1f, 2f, 12f)
        GLU.gluLookAt(gl10, 0f, 0f, 10.0f, 0f, 0f, 0f, 0f, 1f, 0f)
    }

    override fun onDrawFrame(gl10: GL10) {
        //背景色　background color
        gl10.glClearColor(0.1f,0.1f,0.1f,1.0f)
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT)

        ballList(num)
        //玉の色 ball color
        gl10.glColor4f(0.1f,0.7f,0.9f,1.0f)

        for (i in 1..num) {
            beforeBall(i)
            buildBall(i)
            drawCircle(gl10, px1,py1,r1)
            val SC = Screen(px1, py1, vx1, vy1, r1, g)
            SC.screen()
            pxn.set(i, SC.px1)
            pyn.set(i, SC.py1)
            vxn.set(i, SC.vx1)
            vyn.set(i, SC.vy1)
        }

        for (i in 1..num - 1) {
            for (j in i + 1..num) {
                buildBall(i)
                buildBall2(j)
                val PE = Penalty(px1, py1, px2, py2, r1, pos, touch)
                PE.penalty()
                if(PE.touch==1) {
                    pxn.set(i, PE.px1)
                    pyn.set(i, PE.py1)
                    pxn.set(j, PE.px2)
                    pyn.set(j, PE.py2)
                    val CO = Collision(vx1, vy1, vx2, vy2, PE.pos, PE.touch)
                    CO.collision()
                    vxn.set(i, CO.vx1)
                    vyn.set(i, CO.vy1)
                    vxn.set(j, CO.vx2)
                    vyn.set(j, CO.vy2)
                    touchn.set(i, CO.touch)
                    touchn.set(j, CO.touch)
                }
            }
        }
        gl10.glColor4f(0.1f,0.7f,0.9f,1.0f)
    }

    //円を書く関数、実際は 12角形
    //Dodecagon instead of a circle
    fun drawCircle(gl10: GL10, p1x:Float, p1y:Float, r:Float){
        val posci = floatArrayOf(
            (p1x+r*cos(0*PI/180).toFloat()), (p1y+r*sin(0*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(30*PI/180).toFloat()), (p1y+r*sin(30*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(60*PI/180).toFloat()), (p1y+r*sin(60*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(90*PI/180).toFloat()), (p1y+r*sin(90*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(120*PI/180).toFloat()), (p1y+r*sin(120*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(150*PI/180).toFloat()), (p1y+r*sin(150*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(180*PI/180).toFloat()), (p1y+r*sin(180*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(210*PI/180).toFloat()), (p1y+r*sin(210*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(240*PI/180).toFloat()), (p1y+r*sin(240*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(270*PI/180).toFloat()), (p1y+r*sin(270*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(300*PI/180).toFloat()), (p1y+r*sin(300*PI/180).toFloat()), 0.0f,
            (p1x+r*cos(330*PI/180).toFloat()), (p1y+r*sin(330*PI/180).toFloat()), 0.0f,
        )

        val bbci = ByteBuffer.allocateDirect(posci.size*4)
        bbci.order(ByteOrder.nativeOrder())
        val fbci = bbci.asFloatBuffer()
        fbci.put(posci)
        fbci.position(0)
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl10.glVertexPointer(3, GL10.GL_FLOAT,0,fbci)
        gl10.glLineWidth(3.0f)
        gl10.glDrawArrays(GL10.GL_LINE_LOOP, 0, 12)
    }

}