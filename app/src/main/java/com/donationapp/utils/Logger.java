package com.donationapp.utils;

import android.util.Log;


public class Logger {
    private static boolean enabled = true;
    public static final int DEBUG = 1;
    public static final int ERROR = 2;
    public static final int INFO = 3;
    public static final int VERBOSE = 4;
    public static final int WARN = 5;

    public static void log(String tag, String msg, int type) {
        switch (type) {
            case DEBUG:
                debugLog(tag == null ? "null" : tag, msg == null ? "null" : msg);
                break;
            case ERROR:
                errorLog(tag == null ? "null" : tag, msg == null ? "null" : msg);
                break;
            case INFO:
                infoLog(tag == null ? "null" : tag, msg == null ? "null" : msg);
                break;
            case VERBOSE:
                verboseLog(tag == null ? "null" : tag, msg == null ? "null" : msg);
                break;
            case WARN:
                warningLog(tag == null ? "null" : tag, msg == null ? "null" : msg);
                break;
        }
    }

    private static void debugLog(String tag, String msg) {
        if (enabled)
            Log.d(tag, msg);
    }

    private static void errorLog(String tag, String msg) {
        if (enabled)
            Log.e(tag, msg);
    }

    private static void infoLog(String tag, String msg) {
        if (enabled)
            Log.i(tag, msg);
    }

    private static void verboseLog(String tag, String msg) {
        if (enabled)
            Log.v(tag, msg);
    }

    private static void warningLog(String tag, String msg) {
        if (enabled)
            Log.w(tag, msg);
    }
}