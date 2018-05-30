package com.example.administrator.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class LJNView extends View {

    private static final String TAG = "ljn";
    private int colorOne = Color.GRAY;
    private int colorTwo = Color.argb(127, 255, 0, 0);
    private int colorThree = 0xaaff0000;
    private Paint mPaint = new Paint();
    private int mWidth, mHeight;

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    public LJNView(Context context) {
        super(context);
    }

    public LJNView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LJNView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LJNView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
//--------------------------------------------------------构造函数↑-----------------------------------------------------------------------------


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //取出宽度的确切数值
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        //取出宽度的测量模式
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        //取出高度的确切数值
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        //取出高度的测量模式
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        //获取到之前的测量结果  经过计算等....
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        //保存新的结果
        setMeasuredDimension(measuredWidth, measuredHeight);
        Log.i(TAG, "onMeasure: " + "\n"
                + widthMeasureSpec + "\n"
                + heightMeasureSpec + "\n"
                + measuredHeight + "\n"
                + measuredWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: " + "\n"
                + w + "\n"
                + h + "\n"
                + oldw + "\n"
                + oldh + "\n");
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout: " + "\n"
                + left + "\n"
                + top + "\n"
                + right + "\n"
                + bottom + "\n");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawColor(Color.WHITE);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: " + "\n"
                //触摸点相对于其所在组件坐标系的坐标
                + "getX--" + event.getX() + "\n"
                + "getY--" + event.getY() + "\n"
                //触摸点相对于屏幕默认坐标系的坐标
                + "getRawX--" + event.getRawX() + "\n"
                + "getRawY--" + event.getRawY());
        return super.onTouchEvent(event);
    }
}
