package com.djp.easy_push_xiaomi.util;

import android.os.Looper;

import android.os.Handler;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;


public class Utils {

    private final static Handler MAIN_LOOPER_HANDLE = new Handler(Looper.getMainLooper());

    public static <T> T getParam(MethodCall methodCall, MethodChannel.Result result, String paramKey) {
        T param = methodCall.argument(paramKey);
//        if (param == null) {
//            throw new RuntimeException("param not found");
//        }

        return param;
    }

    public static void runMainThread(Runnable runnable) {
        MAIN_LOOPER_HANDLE.post(runnable);
    }
}
