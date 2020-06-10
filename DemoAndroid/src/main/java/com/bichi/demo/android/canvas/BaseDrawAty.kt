package com.bichi.demo.android.canvas

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.bichi.demo.android.R
import com.bichi.lib.common.android.ui.BaseActivity

class BaseDrawAty : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DemoView(this))
    }
}

class DemoView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    val mPaint = Paint()
    var mTempBmp: Bitmap? = null

    init {
        mTempBmp = BitmapFactory.decodeResource(resources, R.drawable.ic_demo_android_icon)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = Color.RED//设置画笔颜色
        mPaint.strokeWidth = 10f//设置画笔宽
        canvas?.apply {
            //点
            drawPoint(100f, 100f, mPaint)
            //线
            drawLine(300f, 100f, 500f, 300f, mPaint)

            //圆 中心坐标（x,y）,半径
            mPaint.color = Color.BLUE
            drawCircle(600f, 200f, 100f, mPaint)

            //空心圆
            mPaint.style = Paint.Style.STROKE//默认是Fill实心的
            mPaint.color = Color.GREEN
            drawCircle(900f, 200f, 100f, mPaint)

            //矩形
            drawRect(100f, 400f, 400f, 700f, mPaint)
            //圆角矩形
            mPaint.color = Color.YELLOW
            drawRoundRect(500f, 400f, 800f, 700f, 45f, 45f, mPaint)

            mPaint.color = Color.CYAN
            mPaint.style = Paint.Style.FILL
            //弧形 0f:从哪个角度开始（3点钟方向） 270f:顺时针扫过多少角度 false:不过中心
            drawArc(100f, 800f, 300f, 1000f, 0f, 270f, false, mPaint)
            //弧形 0f:从哪个角度开始（3点钟方向） 270f:顺时针扫过多少角度 true:过中心
            drawArc(310f, 800f, 510f, 1000f, 0f, 270f, true, mPaint)
            mPaint.style = Paint.Style.STROKE
            //弧形 0f:从哪个角度开始（3点钟方向） 270f:顺时针扫过多少角度 false:不过中心
            drawArc(520f, 800f, 720f, 1000f, 0f, 270f, false, mPaint)
            //弧形 0f:从哪个角度开始（3点钟方向） 270f:顺时针扫过多少角度 true:过中心
            drawArc(780f, 800f, 980f, 1000f, 0f, 270f, true, mPaint)

            mPaint.color = Color.BLACK
            //椭圆
            drawOval(100f, 1100f, 600f, 1300f, mPaint)

            //文字
//            mPaint.strokeWidth = 2f
            mPaint.style = Paint.Style.FILL
            mPaint.textSize = 50f
            drawText("flycatdeng canvas demo", 100f, 1400f, mPaint)

            //Bitmap
            mTempBmp?.let {
                drawBitmap(it, 100f, 1500f, null)
            }
        }
    }
}