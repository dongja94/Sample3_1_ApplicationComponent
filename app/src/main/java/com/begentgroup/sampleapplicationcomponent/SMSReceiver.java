package com.begentgroup.sampleapplicationcomponent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "SMS Receive", Toast.LENGTH_SHORT).show();
        context.startService(new Intent(context, MyService.class));
    }
}
