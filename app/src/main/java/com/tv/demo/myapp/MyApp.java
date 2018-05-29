package com.tv.demo.myapp;

import android.app.Application;

/**
 * Created by Krot on 5/29/18.
 */

public class MyApp extends Application {

    private static MyApp instance;

    public MyApp() {
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }

}
