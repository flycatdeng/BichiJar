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

class CanvasSkewAty : BaseActivity() {
    private var skewFrom =
        0//0:no change  1:skew

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val llRoot = LinearLayout(this)
        val btnRoot = LinearLayout(this)
        setContentView(llRoot)
        val showView = DemoView(this)
        val skew = Button(this).apply {
            setText("错切")
            setOnClickListener {
                skewFrom = 1
                showView.invalidate()
            }
        }
        val noChange = Button(this).apply {
            setText("还原")
            setOnClickListener {
                skewFrom = 0
                showView.invalidate()
            }
        }
        btnRoot.orientation = LinearLayout.HORIZONTAL
        btnRoot.addView(skew)
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
                    when (skewFrom) {
                        1 -> skew(1.2f, 1.1f)
                        else -> {
                        }//no translate
                    }
                    drawBitmap(it, mBitmapRect.left.toFloat(), mBitmapRect.top.toFloat(), null)
                }
            }
        }//end of onDraw
    }
}
