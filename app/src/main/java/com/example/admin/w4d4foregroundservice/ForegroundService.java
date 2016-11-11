package com.example.admin.w4d4foregroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by admin on 11/10/2016.
 */
public class ForegroundService extends Service{


    private static final String LOG_TAG = "ForegroundServiceTAG_";
    private static final String MAIN_ACTION = "com.example.action.mainaction";
    private static final String PREV_ACTION = "com.example.action.prevaction";
    private static final String PLAY_ACTION = "com.example.action.playaction";
    private static final String NEXT_ACTION = "com.example.action.nextaction";
    private static final int NOTIFICATION_ID = 101;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand: ");
        if (intent.getAction().equals(MainActivity.START_ACTION)) {
            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Intent previousIntent = new Intent(this, ForegroundService.class);
            previousIntent.setAction(PREV_ACTION);
            PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                    previousIntent, 0);

            Intent playIntent = new Intent(this, ForegroundService.class);
            playIntent.setAction(PLAY_ACTION);
            PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                    playIntent, 0);

            Intent nextIntent = new Intent(this, ForegroundService.class);
            nextIntent.setAction(NEXT_ACTION);
            PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                    nextIntent, 0);

            Bitmap icon = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_music);

            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("Music Player")
                    .setTicker("Music Player")
                    .setContentText("My Music")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(
                            Bitmap.createScaledBitmap(icon, 128, 128, false))
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .addAction(android.R.drawable.ic_media_previous,
                            "Previous", ppreviousIntent)
                    .addAction(android.R.drawable.ic_media_play, "Play",
                            pplayIntent)
                    .addAction(android.R.drawable.ic_media_next, "Next",
                            pnextIntent).build();
            startForeground(NOTIFICATION_ID,
                    notification);
        } else if (intent.getAction().equals(PREV_ACTION)) {
            Log.i(LOG_TAG, "Clicked Previous");
        } else if (intent.getAction().equals(PLAY_ACTION)) {
            Log.i(LOG_TAG, "Clicked Play");
        } else if (intent.getAction().equals(NEXT_ACTION)) {
            Log.i(LOG_TAG, "Clicked Next");
        } else if (intent.getAction().equals(
                MainActivity.STOP_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }
        
        return START_STICKY;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
