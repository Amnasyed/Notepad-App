package com.example.notepad.AppDatabase;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="editdetail")
    private String editdetail;
    @ColumnInfo(name="timestamp")

    private String timestamp;
    @ColumnInfo(name="colourCode")
    private String colourCode;
    public User( String editdetail, String timestamp, String colourCode) {
        this.editdetail = editdetail;
        this.timestamp = timestamp;
        this.colourCode = colourCode;
    }

    public String getColourCode() {
        return colourCode;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }
    public User(String editdetail) {
        this.editdetail = editdetail;
    }

    public User() {
    }

    public User(String editdetail, String timestamp) {
        this.editdetail = editdetail;
        this.timestamp = timestamp;
    }

    protected User(Parcel in) {
        id = in.readInt();
        editdetail = in.readString();
        timestamp = in.readString();
        colourCode = in.readString();
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

    public String getEditdetail() {
        return editdetail;
    }

    public void setEditdetail(String editdetail) {
        this.editdetail = editdetail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(editdetail);
        parcel.writeString(timestamp);
        parcel.writeString(colourCode);
    }


}
