package com.ibrahim.focustimer.util;

import android.os.CountDownTimer;
import android.widget.TextView;

public class ToggleTimer {

    private TextView textView;
    private CountDownTimer countDownTimer;
    private long millisLeft;
    private long countDownInterval;

    public ToggleTimer(long millisInFuture, long countDownInterval, TextView textView) {
        this.countDownTimer = new MyCountDownTimer(millisInFuture, countDownInterval);
        this.millisLeft = millisInFuture;
        this.countDownInterval = countDownInterval;
        this.textView = textView;
    }

    private String millisToString(long mills) {
        int minutes = (int) (mills / (1000 * 60)) % 60;
        int seconds = (int) (mills / 1000) % 60;

        return String.format("%d:%d", minutes, seconds);
    }

    public void start() {
        countDownTimer.start();
    }

    public void pause() {
        countDownTimer.cancel();
    }

    public void resume() {
        countDownTimer = new MyCountDownTimer(millisLeft, countDownInterval);
        countDownTimer.start();
    }


    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            millisLeft = millisUntilFinished;
            String time = millisToString(millisUntilFinished);
            textView.setText(time);
        }

        @Override
        public void onFinish() {
        }
    }
}