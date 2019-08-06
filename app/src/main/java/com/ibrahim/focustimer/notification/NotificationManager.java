package com.ibrahim.focustimer.notification;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.ibrahim.focustimer.constant.State;

public class NotificationManager {

    private static final String CHANNEL_ID = "Timer";

    private Context context;
    private NotificationManagerCompat manager;
    private State state;

    private NotificationCompat.Builder workTimerBuilder, shortBreakTimeBuilder, longBreakTimeBuilder;

    public NotificationManager(Context context) {
        this.context = context;
        manager = NotificationManagerCompat.from(context);
        state = State.WORK;
        createNotificationChannel();

        workTimerBuilder = NotificationBuilders.getWorkTimerNotificationBuilder(context, CHANNEL_ID);
        shortBreakTimeBuilder = NotificationBuilders.getShortBreakTimerNotificationBuilder(context, CHANNEL_ID);
        longBreakTimeBuilder = NotificationBuilders.getLongBreakTimerNotificationBuilder(context, CHANNEL_ID);
    }

    public void notify(String newTime) {
        if (state == State.WORK) {
            workTimerBuilder.setContentText(newTime);
            manager.notify(1, workTimerBuilder.build());
        } else if (state == State.SHORT_BREAK) {
            shortBreakTimeBuilder.setContentText(newTime);
            manager.notify(1, shortBreakTimeBuilder.build());
        } else if (state == State.LONG_BREAK) {
            longBreakTimeBuilder.setContentText(newTime);
            manager.notify(1, longBreakTimeBuilder.build());
        }
    }

    public void setState(State state) {
        if (state == State.WORK)
            state = State.WORK;
        if (state == State.SHORT_BREAK)
            state = State.SHORT_BREAK;
        if (state == State.LONG_BREAK)
            state = State.LONG_BREAK;
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

