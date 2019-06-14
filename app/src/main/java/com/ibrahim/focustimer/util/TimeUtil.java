package com.ibrahim.focustimer.util;

public class TimeUtil {

    // TODO: This is the maximum, try and increase it
    public static final long MAX_MILLIS_TO_STRING_CONVERSION = 3599000;

    public static String millisToString(long millis) {

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
