package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Leave_List_Item implements Parcelable {

    String name, r_date,r_time,reason,address,mobile,id;

    protected Leave_List_Item(Parcel in) {
        name = in.readString();
        r_date = in.readString();
        r_time = in.readString();
        reason = in.readString();
        address = in.readString();
        mobile = in.readString();
        id = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    public String getR_time() {
        return r_time;
    }

    public void setR_time(String r_time) {
        this.r_time = r_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static final Creator<Leave_List_Item> CREATOR = new Creator<Leave_List_Item>() {
        @Override
        public Leave_List_Item createFromParcel(Parcel in) {
            return new Leave_List_Item(in);
        }

        @Override
        public Leave_List_Item[] newArray(int size) {
            return new Leave_List_Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(r_date);
        dest.writeString(r_time);
        dest.writeString(reason);
        dest.writeString(address);
        dest.writeString(mobile);
        dest.writeString(id);
    }
}
