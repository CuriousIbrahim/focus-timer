package com.ibrahim.focustimer;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ibrahim.focustimer.notification.NotificationManager;


public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "Timer";

    private TextView timeTextView;

    private Button startAndStopToggle;
    private Button stop;

    private MainActivityViewModel viewModel;

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notificationManager = new NotificationManager(this);

        notificationManager.createNotificationChannel();

        notificationManager.showNotification();

        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.time);
        startAndStopToggle = findViewById(R.id.startAndStopToggle);
        stop = findViewById(R.id.stop);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getTimeLeft().observe(this, timeLeft -> {
            Log.d("MainActivity", String.format("Time left: %s", timeLeft));
            timeTextView.setText(timeLeft);
        });

        viewModel.getOnResume().observe(this, (onResume) -> {
            if (onResume) {
                startAndStopToggle.setText("Pause");
            } else {
                startAndStopToggle.setText("Resume");
            }
        });

        startAndStopToggle.setOnClickListener((a) -> viewModel.toggleTime());

        stop.setOnClickListener((a) -> viewModel.stopTime());
    }
}
