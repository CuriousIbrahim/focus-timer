package com.ibrahim.focustimer.data;

import android.os.CountDownTimer;

public abstract class ExtendedCountDownTimer {

    private CountDownTimer countDownTimer;

    private final long originalMillisInFuture;
    private long millisLeft;
    private final long countDownInterval;


    public ExtendedCountDownTimer(long millisInFuture, long countDownInterval) {
        countDownTimer = new MyCountDownTimer(millisInFuture, countDownInterval);
        millisLeft = millisInFuture;
        originalMillisInFuture = millisInFuture;
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

    public void stop() {
        countDownTimer.cancel();
        countDownTimer = new MyCountDownTimer(originalMillisInFuture, countDownInterval);
        millisLeft = originalMillisInFuture;
    }

    private void setMillisLeft(long millisLeft) {
        this.millisLeft = millisLeft;
    }

    public long getMillisLeft() {
        return millisLeft;
    }

    public void reset() {
        countDownTimer.cancel();
        countDownTimer = new MyCountDownTimer(originalMillisInFuture, countDownInterval);
        millisLeft = originalMillisInFuture;
    }

    public abstract void onMyTick(long millisUntilFinished);

    public abstract void onMyFinish();


    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            setMillisLeft(millisUntilFinished);
            onMyTick(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            onMyFinish();
        }
    }
}
