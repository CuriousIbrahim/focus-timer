package com.ibrahim.focustimer;

import com.ibrahim.focustimer.data.PomodoroCycle;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PomodoroCycleTest {

    public static class InitOfPomodoroCycle {
        @Test
        public void checkWorkStateTest() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();

            assertEquals(true, pomodoroCycle.isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();

            assertEquals(false, pomodoroCycle.isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();

            assertEquals(false, pomodoroCycle.isLongBreakState());
        }
    }

    public static class Next1OfPomodoroCycle {
        public PomodoroCycle getPomodoroCycle() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();
            pomodoroCycle.next();
            return pomodoroCycle;
        }

        @Test
        public void checkWorkStateTest() {
            assertEquals(false, getPomodoroCycle().isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            assertEquals(true, getPomodoroCycle().isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isLongBreakState());
        }
    }

    public static class Next2OfPomodoroCycle {
        public PomodoroCycle getPomodoroCycle() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();
            pomodoroCycle.next();
            pomodoroCycle.next();
            return pomodoroCycle;
        }

        @Test
        public void checkWorkStateTest() {
            assertEquals(true, getPomodoroCycle().isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isLongBreakState());
        }
    }

    public static class Next3OfPomodoroCycle {
        public PomodoroCycle getPomodoroCycle() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            return pomodoroCycle;
        }

        @Test
        public void checkWorkStateTest() {
            assertEquals(false, getPomodoroCycle().isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            assertEquals(true, getPomodoroCycle().isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isLongBreakState());
        }
    }

    public static class Next4OfPomodoroCycle {
        public PomodoroCycle getPomodoroCycle() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            return pomodoroCycle;
        }

        @Test
        public void checkWorkStateTest() {
            assertEquals(true, getPomodoroCycle().isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isLongBreakState());
        }
    }

    public static class Next5OfPomodoroCycle {
        public PomodoroCycle getPomodoroCycle() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            return pomodoroCycle;
        }

        @Test
        public void checkWorkStateTest() {
            assertEquals(false, getPomodoroCycle().isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            assertEquals(true, getPomodoroCycle().isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isLongBreakState());
        }
    }

    public static class Next6OfPomodoroCycle {
        public PomodoroCycle getPomodoroCycle() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            return pomodoroCycle;
        }

        @Test
        public void checkWorkStateTest() {
            assertEquals(true, getPomodoroCycle().isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isLongBreakState());
        }
    }

    public static class Next7OfPomodoroCycle {
        public PomodoroCycle getPomodoroCycle() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            return pomodoroCycle;
        }

        @Test
        public void checkWorkStateTest() {
            assertEquals(false, getPomodoroCycle().isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            assertEquals(true, getPomodoroCycle().isLongBreakState());
        }
    }

    public static class Next8OfPomodoroCycle {
        public PomodoroCycle getPomodoroCycle() {
            PomodoroCycle pomodoroCycle = new PomodoroCycle();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            pomodoroCycle.next();
            return pomodoroCycle;
        }

        @Test
        public void checkWorkStateTest() {
            assertEquals(true, getPomodoroCycle().isWorkState());
        }

        @Test
        public void checkShortBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isShortBreakState());
        }

        @Test
        public void checkLongBreakStateTest() {
            assertEquals(false, getPomodoroCycle().isLongBreakState());
        }
    }

}
