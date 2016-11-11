package com.danny.android.androidplugin.hooks;

import android.app.Activity;
import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by danny on 16/11/4.
 */

public class HooksAPI {

    // 注意这里使用的ApplicationContext 启动的Activity
    // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
    // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
    // 比较简单, 直接替换这个Activity的此字段即可.
    public static void attachApplicationContext() throws Exception{
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        // 拿到原始的 mInstrumentation字段
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

        // 创建代理对象
        Instrumentation hooksInstrumentation = new HookInstrumentation(mInstrumentation);

        // 偷梁换柱
        mInstrumentationField.set(currentActivityThread, hooksInstrumentation);
    }

    public static void attachActivityContext(Activity activity) throws Exception{
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.Activity");

        // 拿到原始的 mInstrumentation字段
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(activity);

        // 创建代理对象
        Instrumentation hooksInstrumentation = new HookInstrumentation(mInstrumentation);

        // 偷梁换柱
        mInstrumentationField.set(activity, hooksInstrumentation);
    }
}
