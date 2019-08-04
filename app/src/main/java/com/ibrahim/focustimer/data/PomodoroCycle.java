package com.ibrahim.focustimer.data;

import com.ibrahim.focustimer.constant.State;

public class PomodoroCycle {

    private State currentState;

    private int workCount;

    public PomodoroCycle() {
        currentState = State.WORK;
        workCount = 0;
    }

    public void next() {

        if (currentState == State.WORK) {
            workCount++;
            if (workCount == 4) {
                currentState = State.LONG_BREAK;
                workCount = 0;
            } else {
                currentState = State.SHORT_BREAK;
            }
        } else if (currentState == State.SHORT_BREAK) {
            currentState = State.WORK;
        } else if (currentState == State.LONG_BREAK) {
            currentState = State.WORK;
        }
    }

    public boolean isWorkState() {
        return currentState == State.WORK;
    }

    public boolean isShortBreakState() {
        return currentState == State.SHORT_BREAK;
    }

    public boolean isLongBreakState() {
        return currentState == State.LONG_BREAK;
    }
}
