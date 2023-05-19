package com.quranic.islam.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.quranic.islam.MyApplication;
import com.quranic.islam.SingletonClass;

public class BaseActivity extends AppCompatActivity {
    public Activity mAct = BaseActivity.this;
    public MyApplication app;
    public SingletonClass ST;

    public String DEFAULT_BROADCAST = "NOTIFICATION_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        initBaseActivity();
    }

    private void initBaseActivity() {
        app = (MyApplication) getApplication();
        ST = SingletonClass.getInstance();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent != null && intent.getAction().equals(DEFAULT_BROADCAST)) {

                }
            } catch (Exception ignored) {
                Log.d("roadVast", ignored.getLocalizedMessage());
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (receiver != null)
            LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (receiver != null)
            LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,
                new IntentFilter(DEFAULT_BROADCAST));
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
