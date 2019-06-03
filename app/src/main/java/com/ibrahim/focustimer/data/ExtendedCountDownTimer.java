package com.ibrahim.focustimer.data;

import android.os.CountDownTimer;

public abstract class ExtendedCountDownTimer {

    private CountDownTimer countDownTimer;

    private long millisLeft;
    private long countDownInterval;


    public ExtendedCountDownTimer(long millisInFuture, long countDownInterval) {
        countDownTimer = new MyCountDownTimer(millisInFuture, countDownInterval);
        millisLeft = millisInFuture;
        this.countDownInterval = countDownInterval;
    }

    public void start() {
        countDownTimer.start();
    }

    public void resume() {
        countDownTimer = new MyCountDownTimer(millisLeft, countDownInterval);
        countDownTimer.start();
    }

    public void pause() {
        countDownTimer.cancel();
    }

    public abstract void onTick(long millisUntilFinished);

    public abstract void onFinish();


    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            millisLeft = millisUntilFinished;
            onTick(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            onFinish();
        }
    }
}
