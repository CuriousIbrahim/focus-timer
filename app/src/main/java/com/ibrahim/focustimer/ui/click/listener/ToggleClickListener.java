package com.ibrahim.focustimer.ui.click.listener;

import android.view.View;
import android.widget.TextView;

public class ToggleClickListener implements View.OnClickListener {

    private TextView textView;
    private String toggleText0;
    private String toggleText1;

    protected boolean onState0;

    public ToggleClickListener(TextView textView, String toggleText0, String toggleText1) {
        this.textView = textView;
        this.toggleText0 = toggleText0;
        this.toggleText1 = toggleText1;

        onState0 = true;
    }

    @Override
    public void onClick(View v) {
        toggle();
    }

    public void toggle() {
        if (onState0) {
            toggleState0();
        } else {
            toggleState1();
        }
    }

    public void toggleState0() {
        onState0 = true;
        textView.setText(toggleText0);
    }

    public void toggleState1() {
        onState0 = false;
        textView.setText(toggleText1);
    }


}
