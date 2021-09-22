package com.example.notepad.AppDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getalluser();

    /* * Insert the object in database
     * @param note, object to be inserted
     */
    //insert query for User table
    @Insert
    void insert(User user);

    /* @Update
    public default void updateall(User user)
     {

     }
    */ @Update
    void update(User repos);
    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    //Delete query for User table
    @Delete
    void delete(User user);



    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void deleteall(User... user);
    // Note... is varargs, here note is an array

}

