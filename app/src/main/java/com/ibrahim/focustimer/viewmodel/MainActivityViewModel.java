package com.ibrahim.focustimer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;

import com.ibrahim.focustimer.constant.C;
import com.ibrahim.focustimer.constant.State;
import com.ibrahim.focustimer.data.ExtendedCountDownTimer;
import com.ibrahim.focustimer.data.PomodoroCycle;
import com.ibrahim.focustimer.util.TimeUtil;


public class MainActivityViewModel extends ViewModel {

    private Context context;

    private final ExtendedCountDownTimer workCountDownTimer;
    private final ExtendedCountDownTimer shortBreakCountDownTimer;
    private final ExtendedCountDownTimer longBreakCountDownTimer;

    private MutableLiveData<String> timeLeft;
    private MutableLiveData<Boolean> onResume;
    private MutableLiveData<Boolean> waitingOnUserToContinue;

    private MutableLiveData<State> state;

    private final PomodoroCycle pomodoroCycle;

    private static long time;

    private boolean started;

    public MainActivityViewModel() {

        pomodoroCycle = new PomodoroCycle();

        workCountDownTimer = new MyCountDownTimer(C.DEFAULT_WORK_TIME, C.SECOND);
        shortBreakCountDownTimer = new MyCountDownTimer(C.DEFAULT_SHORT_REST_TIME, C.SECOND);
        longBreakCountDownTimer = new MyCountDownTimer(C.DEFAULT_LONG_REST_TIME, C.SECOND);

        time = C.DEFAULT_WORK_TIME;

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LiveData<Boolean> getWaitingOnUserToContinue() {
        if (waitingOnUserToContinue == null) {
            waitingOnUserToContinue = new MutableLiveData<>();
        }

        return waitingOnUserToContinue;
    }

    public void setWaitingOnUserToContinueToFalse() {
        waitingOnUserToContinue.setValue(false);
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

    public LiveData<State> getState() {
        if (state == null) {
            state = new MutableLiveData<>();
            state.setValue(State.WORK);
        }

        return state;
    }

    private void updateState() {
        if (pomodoroCycle.isWorkState()) {
            state.setValue(State.WORK);
        } else if (pomodoroCycle.isShortBreakState()) {
            state.setValue(State.SHORT_BREAK);
        } else if (pomodoroCycle.isLongBreakState()) {
            state.setValue(State.LONG_BREAK);
        }
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

    private void nextState() {
        getCountDownTimer().reset();
        pomodoroCycle.next();
        time = getTimeBasedOnPomodoroState();
        timeLeft.setValue(TimeUtil.millisToString(time));
        onResume.setValue(false);
        updateState();
    }

    public void skipState() {
        nextState();
        startTime();
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
        timeLeft.setValue(TimeUtil.millisToString(C.DEFAULT_WORK_TIME));
        onResume.setValue(false);
        pomodoroCycle.reset();
        state.setValue(pomodoroCycle.getCurrentState());
    }

    private void pauseTime() {
        workCountDownTimer.pause();
        onResume.setValue(false);
    }

    private String getTextForNotificationMessage() {
        String text = "Timer finished";

        if (state.getValue() == State.WORK)
            text = "Work timer finished";
        else if (state.getValue() == State.SHORT_BREAK)
            text = "Short break timer finished";
        else if (state.getValue() == State.LONG_BREAK)
            text = "Long break timer finished";

        return text;
    }

    private void notifyUser() {
        Intent intent = new Intent();
        intent.setAction("com.ibrahim.NOTIFY");
        intent.putExtra("message", getTextForNotificationMessage());
        context.sendBroadcast(intent);
    }

    class MyCountDownTimer extends ExtendedCountDownTimer {

        MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onMyTick(long millisUntilFinished) {
            timeLeft.setValue(TimeUtil.millisToString(millisUntilFinished));
        }

        @Override
        public void onMyFinish() {
            nextState();
            waitingOnUserToContinue.setValue(true);
            notifyUser();
        }
    }

}
