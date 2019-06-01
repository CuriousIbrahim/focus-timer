package com.ibrahim.focustimer.ui.click.listener;

import android.view.View;
import android.widget.TextView;

import com.ibrahim.focustimer.util.ToggleSelector;
import com.ibrahim.focustimer.ui.util.TimerTextViewToggle;

public class ToggleWithTimerListener implements View.OnClickListener {

    private TimerTextViewToggle timer;
    private TextView textView;

    private String resumeText;
    private String pauseText;

    // State 0 = resume; State 1 = pause
    private ToggleSelector<String> toggleSelector;

    public ToggleWithTimerListener(TextView textView, String resumeText,
                                   String pauseText, TimerTextViewToggle timer) {
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
