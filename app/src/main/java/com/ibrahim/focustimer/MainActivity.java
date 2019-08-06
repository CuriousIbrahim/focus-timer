package com.ibrahim.focustimer;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ibrahim.focustimer.constant.State;
import com.ibrahim.focustimer.notification.NotificationManager;


public class MainActivity extends AppCompatActivity {

    private TextView timeTextView;

    private Button startAndStopToggle;
    private Button stop;

    private MainActivityViewModel viewModel;

    private NotificationManager notificationManager;

    private State state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        notificationManager = new NotificationManager(this);

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

        viewModel.getState().observe(this, state-> {
            this.state = state;
        });

        viewModel.getWaitingOnUserToContinue().observe(this, waitingOnUser -> {

            if (waitingOnUser == true) {
                viewModel.setWaitingOnUserToContinueToFalse();

                String text = "Timer finished";

                if (state == State.WORK)
                    text = "Work timer finished";
                else if (state == State.SHORT_BREAK)
                    text = "Short break timer finished";
                else if (state == State.LONG_BREAK)
                    text = "Long break timer finished";

                notificationManager.notify(text);
            }

        });

        startAndStopToggle.setOnClickListener((a) -> {
            viewModel.toggleTime();
//            viewModel.setWaitingOnUserToContinueToFalse();
        });

        stop.setOnClickListener((a) -> viewModel.stopTime());
    }
}
