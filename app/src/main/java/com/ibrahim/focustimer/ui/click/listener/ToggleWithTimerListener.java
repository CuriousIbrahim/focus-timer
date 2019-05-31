package com.ibrahim.focustimer.ui.click.listener;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ibrahim.focustimer.util.ToggleSelector;
import com.ibrahim.focustimer.util.ToggleTimer;

public class ToggleWithTimerListener implements View.OnClickListener {

    ToggleTimer timer;
    private TextView textView;

    private String resumeText;
    private String pauseText;

    // State 0 = resume; State 1 = pause
    ToggleSelector<String> toggleSelector;

    public ToggleWithTimerListener(TextView textView, String resumeText,
                                   String pauseText, ToggleTimer timer) {
        this.textView = textView;

        this.resumeText = resumeText;
        this.pauseText = pauseText;

        toggleSelector = new ToggleSelector<>(resumeText, pauseText, true);
        this.timer = timer;
    }

    @Override
    public void onClick(View v) {
        if (toggleSelector.isBinaryChoice0Selected()) {
            timer.resume();
            textView.setText(toggleSelector.toggle());
        } else {
            timer.pause();
            textView.setText(toggleSelector.toggle());
        }
    }

    public void setToResume() {
        timer.resume();
        toggleSelector.setToBinaryChoice1();
        textView.setText(toggleSelector.getCurrentState());
    }

    public void setToPause() {
        timer.pause();
        toggleSelector.setToBinaryChoice0();
        textView.setText(toggleSelector.getCurrentState());
    }
}
