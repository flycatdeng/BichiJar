package com.bichi.demo.android.canvas

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.bichi.demo.android.R
import com.bichi.lib.common.android.ui.BaseActivity

class DrawBitmapAty : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DemoView(this))
    }

    inner class DemoView : View {
        constructor(context: Context?) : super(context)
        constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
            context,
            attrs,
            defStyleAttr,
            defStyleRes
        )

        private var mTempBmp: Bitmap? = null
        private var mBitmapRect = Rect()
        private val rectF = RectF(0f, 600f, 400f, 800f)
        private var mMatrix = Matrix()

        init {
            mTempBmp = BitmapFactory.decodeResource(resources, R.drawable.karen).apply {
                mBitmapRect = Rect(0, 0, width, height)
            }
            mMatrix.postScale(1.2f, 1.4f)
            mMatrix.postTranslate(0f, 1100f)
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
                    //1.绘制bitmap原图
                    drawBitmap(it, mBitmapRect.left.toFloat(), mBitmapRect.top.toFloat(), null)
                    //2.1 将bitmap映射到某个区域
                    drawBitmap(it, mBitmapRect, rectF, null)
                    //2.2 选取bitmap中的某一部分映射到某个区域
                    mBitmapRect.inset(100, 100)//原Bitmap中的一部分区域（左右上下全部向内缩小100像素后的区域）
                    rectF.offset(0f, 250f)
                    drawBitmap(it, mBitmapRect, rectF, null)
                    //2.3使用Matrix对图片实现形变后再进行显示
                    drawBitmap(it, mMatrix, null)
                }
            }
        }//end of onDraw
    }
}
