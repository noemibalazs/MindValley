package com.example.android.mindvalley.mindvalley.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pin")
public class PinEntity {

    @PrimaryKey
    @NonNull
    private String id;

    private String name;
    private String profile;
    private String image;

    public PinEntity(String id, String name, String profile, String image){
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.image = image;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
