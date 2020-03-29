package com.example.aidl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;

import java.util.List;

/**
 * User: milan
 * Time: 2020/3/29 14:51
 * Des:
 */
public class AppProcessUtils {
    /**
     * 获取当前App所有进程
     */
    public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcessInfos(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return am.getRunningAppProcesses();
    }

    /**
     * 判断该进程ID是否属于该进程名
     *
     * @param context
     * @param pid     进程ID
     * @param p_name  进程名
     * @return true属于该进程名
     */
    public static boolean isPidOfProcessName(Context context, int pid, String p_name) {
        if (p_name == null)
            return false;
        boolean isMain = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有进程
        for (ActivityManager.RunningAppProcessInfo process : am.getRunningAppProcesses()) {
            if (process.pid == pid) {
                //进程ID相同时判断该进程名是否一致
                if (process.processName.equals(p_name)) {
                    isMain = true;
                }
                break;
            }
        }
        return isMain;
    }

    /**
     * 获取主进程名
     *
     * @param context 上下文
     * @return 主进程名
     */
    public static String getMainProcessName(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).processName;
    }

    public static boolean isMainProcess(Context context) throws PackageManager.NameNotFoundException {
        return isPidOfProcessName(context, Process.myPid(), getMainProcessName(context));
    }
}
