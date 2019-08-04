package com.ibrahim.focustimer.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.ibrahim.focustimer.R;

public class NotificationManager {

    private static final String CHANNEL_ID = "Timer";

    private Context context;
    private NotificationManagerCompat manager;

    public NotificationManager(Context context) {
        this.context = context;
        manager = NotificationManagerCompat.from(context);

        builder = new NotificationCompat.Builder(this.context, CHANNEL_ID)
                .setContentTitle("Timer")
                .setContentText("25:00")
//                .setContentText(context.getString(R.string.))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    public void showNotification() {
        manager.notify(123, getNotification());
    }

    private Notification getNotification() {
        return getNotificationBuilder().build();
    }


    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = "Just showing the timer";
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            android.app.NotificationManager notificationManager = context.getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

