package com.ibrahim.focustimer.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ibrahim.focustimer.notification.NotificationManager;

public class NotificationRequestBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = new NotificationManager(context);

        notificationManager.notify(intent.getStringExtra("message"));
    }

}
