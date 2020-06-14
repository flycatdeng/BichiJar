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

class CanvasTranslateAty : BaseActivity() {
    private var translateFrom =
        0//0:no change  1:translate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val llRoot = LinearLayout(this)
        val btnRoot = LinearLayout(this)
        setContentView(llRoot)
        val showView = DemoView(this)
        val translate = Button(this).apply {
            setText("平移")
            setOnClickListener {
                translateFrom = 1
                showView.invalidate()
            }
        }
        val noChange = Button(this).apply {
            setText("还原")
            setOnClickListener {
                translateFrom = 0
                showView.invalidate()
            }
        }
        btnRoot.orientation = LinearLayout.HORIZONTAL
        btnRoot.addView(translate)
        btnRoot.addView(noChange)
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
                    when (translateFrom) {
                        1 -> translate(100f, 200f)
                        else -> {
                        }//no translate
                    }
                    drawBitmap(it, mBitmapRect.left.toFloat(), mBitmapRect.top.toFloat(), null)
                }
            }
        }//end of onDraw
    }
}
