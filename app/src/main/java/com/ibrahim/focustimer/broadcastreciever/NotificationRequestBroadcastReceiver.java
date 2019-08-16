package com.ibrahim.focustimer.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ibrahim.focustimer.notification.NotificationManager;

public class NotificationRequestBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG  = "NotificationRequestBR";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = new NotificationManager(context);

        notificationManager.notify(intent.getStringExtra("message"));
    }




}
