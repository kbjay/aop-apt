package com.xiaoice.example.apt_api;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * 对外暴露的api
 *
 * @author v-zewan
 * @time 2019/7/1 19:13
 **/
public class BindViewManager {

    public static void bind(Activity activity) {
        //通过反射找到自动生成的类，调用对应的方法
        Class<? extends Activity> clazz = activity.getClass();
        try {
            Class<?> bindClass = clazz.forName(clazz.getName() + "_BindView");
            Method bind = bindClass.getMethod("bind", activity.getClass());
            bind.invoke(bindClass.newInstance(), activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
