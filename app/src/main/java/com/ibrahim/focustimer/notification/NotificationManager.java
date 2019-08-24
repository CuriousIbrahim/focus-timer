package com.ibrahim.focustimer.notification;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.ibrahim.focustimer.R;
import com.ibrahim.focustimer.activity.MainActivity;

public class NotificationManager {

    private static final String CHANNEL_ID = "Timer";

    private Context context;
    private NotificationManagerCompat manager;
    private NotificationCompat.Builder builder;

    public NotificationManager(Context context) {
        this.context = context;
        manager = NotificationManagerCompat.from(context);
        createNotificationChannel();
        builder = getNotificationBuilder();
    }

    public void notify(String text) {
        builder.setContentText(text);

        manager.notify(123, builder.build());
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

    private NotificationCompat.Builder getNotificationBuilder() {

//        Intent intent = new Intent(context, MainActivity.class);
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addNextIntentWithParentStack(intent);
//
//        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_NO_CREATE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Focus Timer")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.work_notification_icon);
//                .setContentIntent(pendingIntent);

        return builder;
    }
}
