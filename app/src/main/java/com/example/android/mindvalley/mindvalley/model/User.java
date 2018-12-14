package com.example.android.mindvalley.mindvalley.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String name;
    private ProfileImage profileImage;

    public User(String name, ProfileImage profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }

    private User(Parcel in) {
        name = in.readString();
        profileImage = in.readParcelable(ProfileImage.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUserName() {
        return name;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(profileImage, flags);
    }
}
