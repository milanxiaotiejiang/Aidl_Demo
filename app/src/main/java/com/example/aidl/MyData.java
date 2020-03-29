package com.example.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User: milan
 * Time: 2020/3/29 16:35
 * Des:
 */
public class MyData implements Parcelable {
    private String data;
    private int type;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.data);
        dest.writeInt(this.type);
    }

    public void readFromParcel(Parcel in) {
        this.data = in.readString();
        this.type = in.readInt();
    }

    public MyData() {
    }

    protected MyData(Parcel in) {
        this.data = in.readString();
        this.type = in.readInt();
    }

    public static final Creator<MyData> CREATOR = new Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel source) {
            return new MyData(source);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };
}
