package com.tv.demo.myapp;

/**
 * Created by Krot on 5/29/18.
 */

public class Utils {

    private Utils() {
    }

    public static int convertDpToPixcel(int dp) {
        return (int) MyApp.getInstance().getResources().getDisplayMetrics().density * dp;
    }


}
