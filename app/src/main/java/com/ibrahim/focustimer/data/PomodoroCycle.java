package com.ibrahim.focustimer.data;

public class PomodoroCycle {

    private static final int WORK_STATE = 0;
    private static final int SHORT_BREAK_STATE = 1;
    private static final int LONG_BREAK_STATE = 2;

    private int currentState;

    private int workCount;

    public PomodoroCycle() {
        currentState = WORK_STATE;
        workCount = 0;
    }

    public void next() {

        if (currentState == WORK_STATE) {
            workCount++;
            if (workCount == 4) {
                currentState = LONG_BREAK_STATE;
                workCount = 0;
            } else {
                currentState = SHORT_BREAK_STATE;
            }
        } else if (currentState == SHORT_BREAK_STATE) {
            currentState = WORK_STATE;
        } else if (currentState == LONG_BREAK_STATE) {
            currentState = WORK_STATE;
        }
    }

    public boolean isWorkState() {
        return currentState == WORK_STATE;
    }

    public boolean isShortBreakState() {
        return currentState == SHORT_BREAK_STATE;
    }

    public boolean isLongBreakState() {
        return currentState == LONG_BREAK_STATE;
    }
}
