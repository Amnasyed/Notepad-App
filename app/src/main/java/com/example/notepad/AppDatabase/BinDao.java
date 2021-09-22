package com.example.notepad.AppDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BinDao {

    @Query("SELECT * FROM Bin")
    List<Bin> getadeleteUser();

    @Query("DELETE FROM Bin")
    public void delete();



    //insert query for User table
    @Insert
    void insert(Bin bin);
    //Insert query for BIn table


    /* @Update

     */ @Update
    void update(Bin repos);


    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    //Delete query for User table
    @Delete
    void delete(Bin bin);


}
