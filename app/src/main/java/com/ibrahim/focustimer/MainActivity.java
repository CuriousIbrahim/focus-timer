package com.ibrahim.focustimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ibrahim.focustimer.ui.click.listener.ToggleWithTimerListener;
import com.ibrahim.focustimer.util.ToggleTimer;

public class MainActivity extends AppCompatActivity {

    private static final long DEFAULT_WORK_TIME = 25 * 60 * 1000;
    private static final long DEFAULT_SHORT_REST_TIME = 5 * 60 * 1000;
    private static final long DEFAULT_LONG_REST_TIME = 30 * 60 * 1000;

    TextView timeTextView;

    Button startAndStopToggle;
    Button stop;

    ToggleTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.time);

        startAndStopToggle = findViewById(R.id.startAndStopToggle);
        stop = findViewById(R.id.stop);

        timer = new ToggleTimer(DEFAULT_WORK_TIME, 1000, timeTextView);

        View.OnClickListener listener = new ToggleWithTimerListener(
                startAndStopToggle, "Resume", "Pause", timer);

        startAndStopToggle.setOnClickListener(listener);
    }


}
