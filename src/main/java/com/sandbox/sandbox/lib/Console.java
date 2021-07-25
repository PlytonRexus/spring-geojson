package com.sandbox.sandbox.lib;

/**
 * Logs to stdout or stderr
 * @category Logging
 * @version 0.0.1
 * 
 */
public class Console {
    private int maxsize = 10000;

    public int getMaxSize() {
        Console tmp = new Console();
        return tmp.maxsize;
    }

    public static void log(String arg) {
        System.out.println(arg);
    }

    public static void log(String[] arg) {
        StringBuilder bld = new StringBuilder();
        for (String x : arg) {
            bld.append(x + " ");
        }
        System.out.println(bld.toString());
    }

    public static void log(int arg) {
        System.out.println(arg);
    }

    public static void log(int[] arg) {
        StringBuilder bld = new StringBuilder();
        for (int x : arg) {
            bld.append(x + " ");
        }
        System.out.println(bld.toString());
    }

    public static void log(boolean arg) {
        System.out.println(arg);
    }

    public static void error(String arg) {
        System.err.println(arg);
    }

    public static void error(String[] arg) {
        StringBuilder bld = new StringBuilder();
        for (String x : arg) {
            bld.append(x + " ");
        }
        System.err.println(bld.toString());
    }

    public static void error(int arg) {
        System.err.println(arg);
    }

    public static void error(int[] arg) {
        StringBuilder bld = new StringBuilder();
        for (int x : arg) {
            bld.append(x + " ");
        }
        System.err.println(bld.toString());
    }

    public static void error(boolean arg) {
        System.err.println(arg);
    }
}