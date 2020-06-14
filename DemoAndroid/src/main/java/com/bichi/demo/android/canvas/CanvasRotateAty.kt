package com.bichi.demo.android.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.bichi.demo.android.R
import com.bichi.lib.common.android.ui.BaseActivity

class CanvasRotateAty : BaseActivity() {
    private var rotateFrom =
        0//0:no rotate  1:rotate across origin   2:rotate across a pointed point(center in this demo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val llRoot = LinearLayout(this)
        val btnRoot = LinearLayout(this)
        setContentView(llRoot)
        val showView = DemoView(this)
        val originRotate = Button(this).apply {
            setText("绕原点旋转")
            setOnClickListener {
                rotateFrom = 1
                showView.invalidate()
            }
        }
        val pointRotate = Button(this).apply {
            setText("绕中心旋转")
            setOnClickListener {
                rotateFrom = 2
                showView.invalidate()
            }
        }
        val noRotate = Button(this).apply {
            setText("还原")
            setOnClickListener {
                rotateFrom = 0
                showView.invalidate()
            }
        }
        btnRoot.orientation = LinearLayout.HORIZONTAL
        btnRoot.addView(originRotate)
        btnRoot.addView(pointRotate)
        btnRoot.addView(noRotate)
        llRoot.orientation = LinearLayout.VERTICAL
        llRoot.addView(btnRoot)
        llRoot.addView(showView)
    }

    inner class DemoView(context: Context?) : View(context) {
        private var mTempBmp: Bitmap? = null
        private var mBitmapRect = Rect()

        init {
            mTempBmp = BitmapFactory.decodeResource(resources, R.drawable.karen).apply {
                mBitmapRect = Rect(0, 0, width, height)
            }
        }

        override fun onDetachedFromWindow() {
            mTempBmp?.apply {
                if (!isRecycled) recycle()
            }
            super.onDetachedFromWindow()
        }

        override fun onDraw(canvas: Canvas?) {
            mTempBmp?.let {
                canvas?.apply {
                    when (rotateFrom) {
                        1 -> rotate(30f)
                        2 -> rotate(30f, mBitmapRect.width() * 0.5f, mBitmapRect.height() * 0.5f)
                        else -> {
                        }//no rotate
                    }
                    drawBitmap(it, mBitmapRect.left.toFloat(), mBitmapRect.top.toFloat(), null)
                }
            }
        }//end of onDraw
    }
}
