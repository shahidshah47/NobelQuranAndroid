package com.quranic.islam.activities;

import android.os.Bundle;
import android.os.Handler;

import androidx.databinding.DataBindingUtil;

import com.quranic.islam.R;
import com.quranic.islam.base.BaseActivity;
import com.quranic.islam.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
    private int TIME_MS = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        ST.startActivity(mAct, MainActivity.class, ST.START_ACTIVITY, ST.START_ACTIVITY_WITH_FINISH);
//        init();
    }

    private void init() {
        startDelay();
    }
    protected void startDelay() {
        new Handler().postDelayed(() -> {
            ST.startActivity(mAct, MainActivity.class, ST.START_ACTIVITY, ST.START_ACTIVITY_WITH_FINISH);
        }, TIME_MS);
    }
}