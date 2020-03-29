package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * User: milan
 * Time: 2020/3/29 14:34
 * Des:
 */
public class AidlService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        boolean mainProcess = false;
        try {
            mainProcess = AppProcessUtils.isMainProcess(this);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("MainActivity", "AidlService onBind 是否在主进程 ： " + mainProcess);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        MyBinder myBinder = new MyBinder();
        Log.e("MainActivity", "service : " + myBinder.toString());
        return myBinder;
    }

    static class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String basicTypes(boolean aBoolean) throws RemoteException {

            return String.valueOf(aBoolean);
        }

        @Override
        public MyData basicMyData(MyData data) throws RemoteException {
            int type = data.getType();
            data.setType(type + 1);
            return data;
        }
    }
}
