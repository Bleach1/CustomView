package com.example.administrator.customview.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.administrator.customview.R;

/**
 * @description:
 * @author: ljn
 * @time: 2018/5/29
 */
public class AnimatorActivity extends AppCompatActivity {

    private ImageView imageView;
    private ViewPropertyAnimator viewPropertyAnimator;
    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        imageView = findViewById(R.id.imageView);
        imageView.post(new Runnable() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                argbEvaluator();
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void viewPropertyAnimator() {
        viewPropertyAnimator = imageView.animate();
        viewPropertyAnimator.translationX(500)
                .setInterpolator(new LinearInterpolator())
                .setDuration(2000)
                //设置一次性的动画开始或结束的监听
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    /**
                     * ViewPropertyAnimator不支持重复执行
                     * @param animation
                     */
                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void objectAnimator() {
        objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 500);
        objectAnimator.setDuration(2000);
        //重复执行
        objectAnimator.setRepeatCount(2);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
        objectAnimator.addPauseListener(new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animation) {

            }

            @Override
            public void onAnimationResume(Animator animation) {

            }
        });
        objectAnimator.start();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void argbEvaluator() {
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator animator = ObjectAnimator.ofInt(imageView, "color", 0xffff0000, 0xff00ff00);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();

       /* @SuppressLint("ObjectAnimatorBinding") ObjectAnimator animator2 = ObjectAnimator.ofArgb(imageView, "color", 0xffff0000, 0xff00ff00);
        animator.start();*/
    }

    /**
     * ofObject() 来对不限定类型的属性做动画
     */
    private void ofObject() {
        ObjectAnimator animator = ObjectAnimator.ofObject(imageView, "position",
                new PointFEvaluator(), new PointF(0, 0), new PointF(1, 1));
        animator.start();


    }

    /**
     * PropertyValuesHolder 同一个动画中改变多个属性
     */
    private void propertyValuesHolder() {

        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 1);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 1);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 1);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder1, holder2, holder3);
        animator.start();
    }

    /**
     * AnimatorSet 多个动画配合执行
     */
    private void animatorSet() {
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationX", 500);
        animator1.setInterpolator(new LinearInterpolator());
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator animator2 = ObjectAnimator.ofInt(imageView, "translationX", 500);
        animator2.setInterpolator(new DecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        // 两个动画依次执行
        animatorSet.playSequentially(animator1, animator2);
        // 两个动画同时执行
        animatorSet.playTogether(animator1, animator2);
        // 使用 AnimatorSet.play(animatorA).with/before/after(animatorB)
        // 的方式来精确配置各个 Animator 之间的关系
        animatorSet.play(animator1).with(animator2);
        animatorSet.play(animator1).before(animator2);
        animatorSet.play(animator1).after(animator2);
        animatorSet.start();

    }

    /**
     * PropertyValuesHolders.ofKeyframe() 把同一个属性拆分
     * 设置 Keyframe （关键帧），把同一个动画属性拆分成多个阶段
     */
    private void ofKeyframe() {
        // 在 0% 处开始
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        // 时间经过 50% 的时候，动画完成度 100%
        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100);
        // 时间见过 100% 的时候，动画完成度倒退到 80%，即反弹 20%
        Keyframe keyframe3 = Keyframe.ofFloat(1, 80);

        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(new SportsView(this), holder);
        animator.start();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStop() {
        super.onStop();
        viewPropertyAnimator.setListener(null);
        viewPropertyAnimator.setUpdateListener(null);

        objectAnimator.removeAllListeners();
        objectAnimator.removeAllUpdateListeners();

    }
}
