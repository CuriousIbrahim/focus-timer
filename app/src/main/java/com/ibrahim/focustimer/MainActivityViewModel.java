package com.ibrahim.focustimer;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ibrahim.focustimer.data.ExtendedCountDownTimer;
import com.ibrahim.focustimer.data.PomodoroCycle;
import com.ibrahim.focustimer.util.TimeUtil;

public class MainActivityViewModel extends ViewModel {

    private ExtendedCountDownTimer workCountDownTimer, shortBreakCountDownTimer, longBreakCountDownTimer;

    private MutableLiveData<String> timeLeft;
    private MutableLiveData<Boolean> onResume;

    PomodoroCycle pomodoroCycle;

    private static long time;

    private boolean started;

    public MainActivityViewModel() {
        pomodoroCycle = new PomodoroCycle();

        workCountDownTimer = new MyCountDownTimer(C.DEFAULT_WORK_TIME, C.SECOND);
        shortBreakCountDownTimer = new MyCountDownTimer(C.DEFAULT_SHORT_REST_TIME, C.SECOND);
        longBreakCountDownTimer = new MyCountDownTimer(C.DEFAULT_LONG_REST_TIME, C.SECOND);

        time = C.DEFAULT_WORK_TIME;

    }

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
            onResume.setValue(false);
        }
        return onResume;
    }

    private void loadTime() {
        started = false;
        timeLeft.setValue(TimeUtil.millisToString(time));
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
            getCountDownTimer().start();
            started = true;
        } else {
            getCountDownTimer().resume();
        }
    }

    private ExtendedCountDownTimer getCountDownTimer() {
        if (pomodoroCycle.isWorkState())
            return workCountDownTimer;
        else if (pomodoroCycle.isShortBreakState())
            return shortBreakCountDownTimer;
        else
            return longBreakCountDownTimer;
    }

    private long getTimeBasedOnPomodoroState() {
        if (pomodoroCycle.isWorkState())
            return C.DEFAULT_WORK_TIME;
        else if (pomodoroCycle.isShortBreakState())
            return C.DEFAULT_SHORT_REST_TIME;
        else
            return C.DEFAULT_LONG_REST_TIME;
    }

    public void stopTime() {
        getCountDownTimer().reset();
        timeLeft.setValue(TimeUtil.millisToString(getCountDownTimer().getMillisLeft()));
        onResume.setValue(false);
    }

    public void pauseTime() {
        workCountDownTimer.pause();
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
            getCountDownTimer().reset();
            pomodoroCycle.next();
            time = getTimeBasedOnPomodoroState();
            timeLeft.setValue(TimeUtil.millisToString(time));
            onResume.setValue(false);
        }
    }

}
