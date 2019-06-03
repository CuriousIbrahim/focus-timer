package com.ibrahim.focustimer.util;

public class TimeUtil {

    public static final String millisToString(long millis) {

        int minutes = (int) (millis / (1000 * 60)) % 60;
        int seconds = (int) (millis / 1000) % 60;

        StringBuilder string = new StringBuilder();

        if (minutes < 10) {
            string.append(0);
        }

        string.append(minutes);

        string.append(":");

        if (seconds < 10) {
            string.append(0);
        }

        string.append(seconds);

        return string.toString();

    }

}
