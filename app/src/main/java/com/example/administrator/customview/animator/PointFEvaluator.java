package com.example.administrator.customview.animator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class PointFEvaluator implements TypeEvaluator<PointF> {

    private PointF pointF = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float x = startValue.x + (fraction * (endValue.x - startValue.x));
        float y = startValue.y + (fraction * (endValue.y - startValue.y));
        pointF.set(x, y);
        return pointF;
    }
}
