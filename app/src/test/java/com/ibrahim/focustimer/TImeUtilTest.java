package com.ibrahim.focustimer;

import com.ibrahim.focustimer.util.TimeUtil;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TImeUtilTest {

    public static class MillisToStringTests {

        @Test
        public void test1() {
            long time = 0;

            assertEquals("00:00", TimeUtil.millisToString(time));
        }

        @Test
        public void test2() {
            long time = 1000 * 60;

            assertEquals("01:00", TimeUtil.millisToString(time));
        }

        @Test
        public void test3() {
            long time = 1000 * 90;

            assertEquals("01:30", TimeUtil.millisToString(time));
        }

        @Test
        public void test4() {
            long time = 1000 * 95;

            assertEquals("01:35", TimeUtil.millisToString(time));
        }

        @Test
        public void test5() {
            long time = 1000 * 60 * 10;

            assertEquals("10:00", TimeUtil.millisToString(time));
        }

        @Test
        public void test6() {
            long time = 1000 * 60 * 25;

            assertEquals("25:00", TimeUtil.millisToString(time));
        }

        @Test
        public void test7() {
            long time = 1000 * 60 * 45;

            assertEquals("45:00", TimeUtil.millisToString(time));
        }

        @Test
        public void test8() {

            long time = TimeUtil.MAX_MILLIS_TO_STRING_CONVERSION;

            assertEquals("59:59", TimeUtil.millisToString(time));
        }

    }
}
