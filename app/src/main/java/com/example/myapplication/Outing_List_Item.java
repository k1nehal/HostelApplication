package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Outing_List_Item implements Parcelable {
    private String Name, Purpose, Place, Time, ID;

    public Outing_List_Item(){}
    protected Outing_List_Item(Parcel in) {
        Name = in.readString();
        Purpose = in.readString();
        Place = in.readString();
        Time = in.readString();
        ID= in.readString();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public static final Creator<Outing_List_Item> CREATOR = new Creator<Outing_List_Item>() {
        @Override
        public Outing_List_Item createFromParcel(Parcel in) {
            return new Outing_List_Item(in);
        }

        @Override
        public Outing_List_Item[] newArray(int size) {
            return new Outing_List_Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Purpose);
        dest.writeString(Place);
        dest.writeString(Time);
        dest.writeString(ID);
    }
}
