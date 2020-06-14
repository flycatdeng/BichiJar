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

class CanvasScaleAty : BaseActivity() {
    private var scaleFrom =
        0//0:no change  1:change across origin   2:change across a pointed point(center in this demo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val llRoot = LinearLayout(this)
        val btnRoot = LinearLayout(this)
        setContentView(llRoot)
        val showView = DemoView(this)
        val originScale = Button(this).apply {
            setText("绕原点缩放")
            setOnClickListener {
                scaleFrom = 1
                showView.invalidate()
            }
        }
        val pointScale = Button(this).apply {
            setText("绕中心缩放")
            setOnClickListener {
                scaleFrom = 2
                showView.invalidate()
            }
        }
        val noChange = Button(this).apply {
            setText("还原")
            setOnClickListener {
                scaleFrom = 0
                showView.invalidate()
            }
        }
        btnRoot.orientation = LinearLayout.HORIZONTAL
        btnRoot.addView(originScale)
        btnRoot.addView(pointScale)
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
                    when (scaleFrom) {
                        1 -> scale(0.5f, 0.5f)
                        2 -> scale(0.5f, 0.5f, mBitmapRect.width() * 0.5f, mBitmapRect.height() * 0.5f)
                        else -> {
                        }//no change
                    }
                    drawBitmap(it, mBitmapRect.left.toFloat(), mBitmapRect.top.toFloat(), null)
                }
            }
        }//end of onDraw
    }
}
