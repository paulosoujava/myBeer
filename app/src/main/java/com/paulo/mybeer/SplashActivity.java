package com.paulo.mybeer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.paulo.mybeer.model.data.presenter.MainActivityPresenter;
import com.paulo.mybeer.ui.MainActivity;

public class SplashActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    ImageView mImageView;
    private static int SPLASH_TIME_OUT = 3000;
    Animation mUp,mDown;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        mLinearLayout =  findViewById(R.id.l1);
        mImageView =  findViewById(R.id.l2);

        mUp = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        mDown = AnimationUtils.loadAnimation(this,R.anim.downtoup);

        mLinearLayout.setAnimation(mUp);
        mImageView.setAnimation(mDown);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
