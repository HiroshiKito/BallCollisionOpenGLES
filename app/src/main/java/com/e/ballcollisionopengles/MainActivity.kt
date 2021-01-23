package com.e.ballcollisionopengles

import android.content.Context
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {

    private lateinit var mGLView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mGLView = MyGLSurfaceView(this)
        setContentView(mGLView)
    }

    override fun onPause() {
        super.onPause()
        mGLView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mGLView.onResume()
    }

    class MyGLSurfaceView(context: Context) : GLSurfaceView(context) {

        private val mRenderer: GLRender

        init {
            setEGLContextClientVersion(1)
            mRenderer = GLRender()
            setRenderer(mRenderer)
        }
    }
}