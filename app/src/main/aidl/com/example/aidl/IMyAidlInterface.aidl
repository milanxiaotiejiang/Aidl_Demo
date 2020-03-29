// IMyAidlInterface.aidl
package com.example.aidl;

// Declare any non-default types here with import statements
import com.example.aidl.MyData;
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String basicTypes(boolean aBoolean);
    MyData basicMyData(inout MyData data);
}
