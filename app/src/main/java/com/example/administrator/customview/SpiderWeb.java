package com.example.administrator.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SpiderWeb extends View {
    /**
     * 中心点坐标
     */
    private int centerX;
    private int centerY;
    private int count = 6;
    private float angle = (float) (Math.PI * 2 / count);
    /**
     * 网格最大半径
     */
    private float radius;
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20};
    private float maxValue = 100;
    private Paint mainPaint;
    private Paint valuePaint;
    private Paint textPaint;
    private Paint circlePaint;

    public SpiderWeb(Context context) {
        super(context);
    }

    public SpiderWeb(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mainPaint = new Paint();
        mainPaint.setColor(Color.BLACK);
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setStrokeWidth(1);
        mainPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setAntiAlias(true);

        valuePaint = new Paint();
        valuePaint.setColor(Color.GREEN);
        valuePaint.setAntiAlias(true);

        circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);
        circlePaint.setAntiAlias(true);
    }

    public SpiderWeb(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SpiderWeb(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 绘制正多边形
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        //r是蜘蛛丝之间的间距
        float r = radius / (count - 1);
        //中心点不用绘制
        for (int i = 1; i < count; i++) {
            //当前半径
            float curR = r * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            //闭合路径
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制直线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {

        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    private static final String TAG = "ljn";

    /**
     * 绘制文本
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        //字体高度
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));
            //第1象限 0~90
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {
                canvas.drawText(titles[i], x, y, textPaint);
                Log.i(TAG, "drawText1: " + titles[i]);
                //第4象限 270~360
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {
                canvas.drawText(titles[i], x, y, textPaint);
                Log.i(TAG, "drawText4: " + titles[i]);
                //第2象限 90~180
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {
                //文本长度
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
                Log.i(TAG, "drawText2: " + titles[i]);
                //第3象限 180~270
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {
                //文本长度
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
                Log.i(TAG, "drawText3: " + titles[i]);
            }


        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 5, circlePaint);
        }
        path.close();
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, valuePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }
}
