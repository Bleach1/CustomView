package com.example.administrator.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @description: 自定义viewgroup
 * @author: ljn
 * @time: 2018/5/30
 */
public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int childWidthSpec, usedWith;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            LayoutParams layoutParams = childView.getLayoutParams();
            int selfWidthMode = MeasureSpec.getMode(widthMeasureSpec);
            int selfWidthSize = MeasureSpec.getSize(widthMeasureSpec);

            switch (layoutParams.width) {

                case LayoutParams.MATCH_PARENT:
                    if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(selfWidthSize - usedWith, MeasureSpec.EXACTLY);
                    } else {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                case LayoutParams.WRAP_CONTENT:
                    if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(selfWidthSize - usedWith, MeasureSpec.AT_MOST);
                    } else {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                default:
                    childWidthSpec = MeasureSpec.makeMeasureSpec(layoutParams.width, MeasureSpec.EXACTLY);
                    break;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(1, 1, 1, 1);
        }
    }


}
