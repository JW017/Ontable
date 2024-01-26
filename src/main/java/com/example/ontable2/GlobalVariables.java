package com.example.ontable2;

public class GlobalVariables {

    private static GlobalVariables instance;

    private boolean isMemoOpened = false;

    private GlobalVariables() {
        // Private constructor to prevent instantiation.
    }

    public static synchronized GlobalVariables getInstance() {
        if (instance == null) {
            instance = new GlobalVariables();
        }
        return instance;
    }

    public boolean isMemoOpened() {
        return isMemoOpened;
    }

    public void setMemoOpened(boolean memoOpened) {
        isMemoOpened = memoOpened;
    }
}
