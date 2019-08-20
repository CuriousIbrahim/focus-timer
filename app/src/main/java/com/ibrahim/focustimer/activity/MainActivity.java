package com.ibrahim.focustimer.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.ibrahim.focustimer.viewmodel.MainActivityViewModel;
import com.ibrahim.focustimer.R;
import com.ibrahim.focustimer.broadcastreciever.NotificationRequestBroadcastReceiver;
import com.ibrahim.focustimer.constant.State;


public class MainActivity extends AppCompatActivity {

    private TextView timeTextView;
    private TextView workText, shortBreakText, longBreakText;

    private Button startAndStopToggle;
    private Button stop;
    private Button next;

    private MainActivityViewModel viewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.button_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpBroadcastReceiver();

        timeTextView = findViewById(R.id.time);

        workText = findViewById(R.id.workText);
        shortBreakText = findViewById(R.id.shortBreakText);
        longBreakText = findViewById(R.id.longBreakWork);

        startAndStopToggle = findViewById(R.id.startAndStopToggle);
        stop = findViewById(R.id.stop);
        next = findViewById(R.id.next);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.setContext(this);

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
            workText.setTypeface(null, Typeface.NORMAL);
            shortBreakText.setTypeface(null, Typeface.NORMAL);
            longBreakText.setTypeface(null, Typeface.NORMAL);

            if (state == State.WORK)
                workText.setTypeface(null, Typeface.BOLD);
            else if (state == State.SHORT_BREAK)
                shortBreakText.setTypeface(null, Typeface.BOLD);
            else if (state == State.LONG_BREAK)
                longBreakText.setTypeface(null, Typeface.BOLD);

        });

        viewModel.getWaitingOnUserToContinue().observe(this, waitingOnUser -> {
            if (waitingOnUser == true) {
                viewModel.setWaitingOnUserToContinueToFalse();
            }
        });

        startAndStopToggle.setOnClickListener((a) -> {
            viewModel.toggleTime();
        });

        stop.setOnClickListener((a) -> viewModel.stopTime());

        next.setOnClickListener(l -> viewModel.skipState());
    }

    private void setUpBroadcastReceiver() {
        BroadcastReceiver br = new NotificationRequestBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ibrahim.NOTIFY");
        registerReceiver(br, filter);
    }
}
