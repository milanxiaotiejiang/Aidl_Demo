package com.example.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("MainActivity", "service : " + service.toString());
                IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                try {
                    String s = iMyAidlInterface.basicTypes(true);
                    boolean mainProcess = AppProcessUtils.isMainProcess(MainActivity.this);
                    Log.e("MainActivity", "onServiceConnected方法 是否在主进程 ： " + mainProcess);
                    Log.e("MainActivity", "basicTypes返回值 ：" + s);
                } catch (RemoteException | PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                MyData myData = new MyData();
                myData.setData("");
                myData.setType(100);
                try {
                    MyData myData1 = iMyAidlInterface.basicMyData(myData);
                    int type = myData1.getType();
                    Log.e("MainActivity", "basicMyData返回值中type = " + type);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }
}
