package com.example.android.mindvalley.mindvalley.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pin implements Parcelable{

    private String pinId;
    private User user;
    private Urls urls;


    public Pin(String pinId, User user, Urls urls) {
        this.pinId = pinId;
        this.user = user;
        this.urls = urls;
    }

    private Pin(Parcel in) {
        pinId = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        urls = in.readParcelable(Urls.class.getClassLoader());
    }

    public static final Creator<Pin> CREATOR = new Creator<Pin>() {
        @Override
        public Pin createFromParcel(Parcel in) {
            return new Pin(in);
        }

        @Override
        public Pin[] newArray(int size) {
            return new Pin[size];
        }
    };

    public String getPinId() {
        return pinId;
    }

    public User getUser() {
        return user;
    }

    public Urls getUrls() {
        return urls;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pinId);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(urls, flags);
    }
}