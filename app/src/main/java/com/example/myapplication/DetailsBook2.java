package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

class DetailsBook2 implements Parcelable {

    String mother,father;
    long mmob,fmob;

    public DetailsBook2(String mother, String father, long mmob, long fmob) {
        this.mother = mother;
        this.father = father;
        this.mmob = mmob;
        this.fmob = fmob;
    }

    public String getMother() {
        return mother;
    }

    public String getFather() {
        return father;
    }

    public long getMmob() {
        return mmob;
    }

    public long getFmob() {
        return fmob;
    }

    public DetailsBook2(Parcel parcel) {
        mother=parcel.readString();
        father=parcel.readString();
        mmob=parcel.readLong();
        fmob=parcel.readLong();
    }

    public static final Parcelable.Creator<DetailsBook2> CREATOR = new Creator<DetailsBook2>() {
        @Override
        public DetailsBook2 createFromParcel(Parcel source) {
            return new DetailsBook2(source);
        }

        @Override
        public DetailsBook2[] newArray(int size) {
            return new DetailsBook2[0];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mother);
        dest.writeString(father);
        dest.writeLong(mmob);
        dest.writeLong(fmob);

    }
}
