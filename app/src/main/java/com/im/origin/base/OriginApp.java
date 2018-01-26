package com.im.origin.base;

import android.app.Application;

import com.im.origin.im.utils.IMBuilder;


/**
 * Created by zc on 2018/1/25.
 */

public class OriginApp extends Application{
    public static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        new IMBuilder(this).init();
    }
}
