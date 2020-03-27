package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Room_LIst_Item implements Parcelable {
    private String Category, Room_No;
    private int Alloted, Seater;

    public Room_LIst_Item() {
    }

    protected Room_LIst_Item(Parcel in) {
        Category = in.readString();
        Room_No = in.readString();
        Alloted = in.readInt();
        Seater = in.readInt();
    }

    public static final Creator<Room_LIst_Item> CREATOR = new Creator<Room_LIst_Item>() {
        @Override
        public Room_LIst_Item createFromParcel(Parcel in) {
            return new Room_LIst_Item(in);
        }

        @Override
        public Room_LIst_Item[] newArray(int size) {
            return new Room_LIst_Item[size];
        }
    };

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getRoom_No() {
        return Room_No;
    }

    public void setRoom_No(String room_No) {
        Room_No = room_No;
    }

    public int getAlloted() {
        return Alloted;
    }

    public void setAlloted(int alloted) {
        Alloted = alloted;
    }

    public int getSeater() {
        return Seater;
    }

    public void setSeater(int seater) {
        Seater = seater;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Category);
        dest.writeString(Room_No);
        dest.writeInt(Alloted);
        dest.writeInt(Seater);
    }
}
