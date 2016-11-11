package com.example.admin.w4d4foregroundservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String START_ACTION = "com.example.action.startforeground";
    public static final String STOP_ACTION = "com.example.action.stopforeground";
    private static final String TAG = "MainTAG_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        Intent startIntent = new Intent(MainActivity.this, ForegroundService.class);
        startIntent.setAction(START_ACTION);
        Log.d(TAG, "start: ");
        startService(startIntent);
        Log.d(TAG, "start: ");
    }

    public void stop(View view) {
        Intent stopIntent = new Intent(MainActivity.this, ForegroundService.class);
        stopIntent.setAction(STOP_ACTION);
        startService(stopIntent);
    }
}
