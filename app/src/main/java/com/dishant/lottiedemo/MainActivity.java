package com.dishant.lottiedemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by DISHANT WALIA on 22-05-2018.
 */

public class MainActivity extends AppCompatActivity {

    private LottieAnimationView animationView;
    private Button btnAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        btnAction = (Button) findViewById(R.id.btn_start);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCheckAnimation();
            }
        });
    }

    private void startCheckAnimation() {
        final ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f).setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animationView.setProgress((Float) valueAnimator.getAnimatedValue());

            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                btnAction.setEnabled(true);
                btnAction.setText(getString(R.string.str_reset));
            }
        });
        toogleButtonAction(animationView, animator);

    }

    private void toogleButtonAction(LottieAnimationView animationView, ValueAnimator animator) {
        if (animationView.getProgress() == 0f) {
            animator.start();
            btnAction.setEnabled(false);
        } else {
            animationView.setProgress(0f);
            btnAction.setEnabled(true);
            btnAction.setText(getString(R.string.str_start));
        }
    }
}
