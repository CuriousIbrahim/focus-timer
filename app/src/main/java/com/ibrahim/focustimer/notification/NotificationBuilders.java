package com.ibrahim.focustimer.notification;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.ibrahim.focustimer.R;

public final class NotificationBuilders {

    public final static NotificationCompat.Builder getWorkTimerNotificationBuilder(Context context, String channelID) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setContentTitle("Timer | Work")
                .setContentText("25:00")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder;

    }

    public final static NotificationCompat.Builder getShortBreakTimerNotificationBuilder(Context context, String channelID) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setContentTitle("Timer | Short Break")
                .setContentText("5:00")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder;

    }

    public final static NotificationCompat.Builder getLongBreakTimerNotificationBuilder(Context context, String channelID) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setContentTitle("Timer | Long Break")
                .setContentText("20:00")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder;

    }
}
