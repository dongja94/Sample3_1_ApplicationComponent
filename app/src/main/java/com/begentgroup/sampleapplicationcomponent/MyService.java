package com.begentgroup.sampleapplicationcomponent;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private static final String TAG = "MyService";

    boolean isRunning = false;
    int count = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate...", Toast.LENGTH_SHORT).show();
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    count++;
                    Log.i(TAG, "count : " + count);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(mReceiver, filter);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
                Toast.makeText(context, "Screen On", Toast.LENGTH_SHORT).show();
            } else if (intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
                Log.i(TAG, "screen off");
            }
        }
    };



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand...", Toast.LENGTH_SHORT).show();
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy...", Toast.LENGTH_SHORT).show();
        isRunning = false;
        unregisterReceiver(mReceiver);
    }
}
