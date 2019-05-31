package com.ibrahim.focustimer.ui.click.listener;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ibrahim.focustimer.util.ToggleTimer;

public class ToggleWithTimerListener extends ToggleClickListener {

    ToggleTimer timer;

    public ToggleWithTimerListener(TextView textView, String toggleText0,
                                   String toggleText1, ToggleTimer timer) {
        super(textView, toggleText0, toggleText1);
        this.timer = timer;
    }

    @Override
    public void onClick(View v) {
        if (onState0) {
            Log.d("DEBUG", "Starting timer");
            timer.resume();
        } else
            timer.pause();
        super.onClick(v);
    }
}
