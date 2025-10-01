package org.example;

public class Utility {
    public static String formatMillisToTime(long millis) {
        long hours = (millis / (1000 * 60 * 60)) % 24;
        long minutes = (millis / (1000 * 60)) % 60;
        long seconds = (millis / 1000) % 60;
        long ms = millis % 1000;

        return String.format(" [[ %02d:%02d:%02d.%03d ]] ---> ", hours, minutes, seconds, ms);
    }

    public static String trimFileName(String filepath) {
        int index = filepath.lastIndexOf('\\');
        return (index != -1) ? filepath.substring(index + 1) : filepath;
    }
}
