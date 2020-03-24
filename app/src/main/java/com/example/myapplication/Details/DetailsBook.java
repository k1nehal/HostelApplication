package com.example.myapplication.Details;

import android.os.Parcel;
import android.os.Parcelable;

class DetailsBook implements Parcelable {
    String fname,lname,pemail,bloodGroup,gender,bday;
    long mobile;
    int height, weight;

    public DetailsBook(String fname, String lname, String pemail, String bloodGroup, String gender, int height, int weight, long  mobile, String bday) {
        this.fname = fname;
        this.lname = lname;
        this.pemail = pemail;
        this.bloodGroup = bloodGroup;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.mobile = mobile;
        this.bday = bday;
    }

    public String getFname() { return fname;     }

    public String getLname() {
        return lname;
    }

    public String getPemail() {
        return pemail;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public long getMobile() {
        return mobile;
    }

    public String getBday() {
        return bday;
    }

    public DetailsBook(Parcel parcel)
    {
        fname=parcel.readString();
        lname=parcel.readString();
        pemail=parcel.readString();
        bloodGroup=parcel.readString();
        gender=parcel.readString();
        height=parcel.readInt();
        weight=parcel.readInt();
        mobile=parcel.readLong();
        bday=parcel.readString();
    }

    public static final Parcelable.Creator<DetailsBook> CREATOR = new Creator<DetailsBook>() {
        @Override
        public DetailsBook createFromParcel(Parcel source) {
            return new DetailsBook(source);
        }

        @Override
        public DetailsBook[] newArray(int size) {
            return new DetailsBook[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(fname);
            dest.writeString(lname);
            dest.writeString(pemail);
            dest.writeString(bloodGroup);
            dest.writeString(gender);
            dest.writeInt(height);
            dest.writeInt(weight);
            dest.writeLong(mobile);
            dest.writeString(bday);
        }
}
