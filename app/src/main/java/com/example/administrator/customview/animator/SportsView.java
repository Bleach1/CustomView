package com.example.administrator.customview.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @description:
 * @author: ljn
 * @time: 2018/5/29
 */
public class SportsView extends View {

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private float progress = 0;

    /**
     * 1.
     */
    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        //界面参数发生改变 需要重绘制
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //1.
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();

        //经过一些计算....
        setMeasuredDimension(measuredWidth, measuredHeight);


    }
}
