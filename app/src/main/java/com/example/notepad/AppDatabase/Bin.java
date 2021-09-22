package com.example.notepad.AppDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Bin {

    @PrimaryKey(autoGenerate = true)
    private int binId;
    @ColumnInfo(name="deletenote")
    private String deletenote;
    @ColumnInfo(name="deletetimeStamp")
    private String deletetimestamp;

    @ColumnInfo(name="deleteColourCode")
    private String deleteColourCode;

    public Bin(int binId, String deletenote, String deletetimestamp, String deleteColourCode) {
        this.binId = binId;
        this.deletenote = deletenote;
        this.deletetimestamp = deletetimestamp;
        this.deleteColourCode = deleteColourCode;
    }


    public String getDeleteColourCode() {
        return deleteColourCode;
    }

    public void setDeleteColourCode(String deleteColourCode) {
        this.deleteColourCode = deleteColourCode;
    }

    public Bin() {
    }

    public Bin(String deletenote, String deletetimestamp) {
        this.deletenote = deletenote;
        this.deletetimestamp = deletetimestamp;
    }

    public Bin(int binId, String deletenote, String deletetimestamp) {
        this.binId = binId;
        this.deletenote = deletenote;
        this.deletetimestamp = deletetimestamp;
    }

    public int getBinId() {
        return binId;
    }

    public void setBinId(int binId) {
        this.binId = binId;
    }

    public String getDeletenote() {
        return deletenote;
    }

    public void setDeletenote(String deletenote) {
        this.deletenote = deletenote;
    }

    public String getDeletetimestamp() {
        return deletetimestamp;
    }

    public void setDeletetimestamp(String deletetimestamp) {
        this.deletetimestamp = deletetimestamp;
    }
}
