package com.example.xiaoby.wechatsport;


import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class HookForWechatSport implements IXposedHookLoadPackage {
    private static int stepCount = 1;
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        //-------------------------------------------------------
        findAndHookMethod("android.hardware.SystemSensorManager$SensorEventQueue",
                loadPackageParam.classLoader,
                "dispatchSensorEvent",
                int.class,
                float[].class,
                int.class,
                long.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("ININININININININI"+stepCount);
                        ((float[]) param.args[1])[0] = ((float[]) param.args[1])[0] + 500* stepCount;
                        stepCount++;
                        XposedBridge.log("RUNRUNRUNRUNRUNR");
                    }
                });
        //-------------------------------------------------------
//        final Class<?> sensorEL = findClass("android.hardware.SystemSensorManager$SensorEventQueue", loadPackageParam.classLoader);
//
//        XposedBridge.hookAllMethods(
//                sensorEL,
//                "dispatchSensorEvent",
//                new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                ((float[]) param.args[1])[0] = ((float[]) param.args[1])[0] + 1168 * stepCount;
//                stepCount++;
//
//                Field field = param.thisObject.getClass().getEnclosingClass().getDeclaredField("sHandleToSensor");
//                field.setAccessible(true);
//                XposedBridge.log("RUNRUNRUNRUNRUNR");
//            }
//        });
    }
}