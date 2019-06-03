package com.ibrahim.focustimer;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ibrahim.focustimer.data.ExtendedCountDownTimer;
import com.ibrahim.focustimer.util.TimeUtil;

public class MainActivityViewModel extends ViewModel {

    private ExtendedCountDownTimer countDownTimer;

    private MutableLiveData<String> timeLeft;
    private MutableLiveData<Boolean> onResume;

    private boolean started;

    public LiveData<String> getTimeLeft() {

        if (timeLeft == null) {
            timeLeft = new MutableLiveData<>();
            loadTime();
        }

        return timeLeft;
    }

    public LiveData<Boolean> getOnResume() {
        if (onResume == null) {
            onResume = new MutableLiveData<>();
            setOnResume();
        }

        return onResume;
    }

    private void loadTime() {
        started = false;
        countDownTimer = new MyCountDownTimer(C.DEFAULT_WORK_TIME, 1000);
        timeLeft.setValue(TimeUtil.millisToString(C.DEFAULT_WORK_TIME));
    }

    private void setOnResume() {
        onResume.setValue(false);
    }

    public void toggleTime() {
        if (onResume.getValue()) {
            pauseTime();
        } else {
            startTime();
        }
    }

    public void startTime() {

        onResume.setValue(true);
        if (!started) {
            countDownTimer.start();
            started = true;
        } else {
            countDownTimer.resume();
        }
    }

    public void stopTime() {
        countDownTimer.stop();
        timeLeft.setValue(TimeUtil.millisToString(countDownTimer.getMillisLeft()));
        onResume.setValue(false);
    }

    public void pauseTime() {
        countDownTimer.pause();
        onResume.setValue(false);
    }

    class MyCountDownTimer extends ExtendedCountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onMyTick(long millisUntilFinished) {
            timeLeft.setValue(TimeUtil.millisToString(millisUntilFinished));
        }

        @Override
        public void onMyFinish() {

        }
    }

}
