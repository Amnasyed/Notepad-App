package com.example.notepad.AppDatabase;

import android.content.Context;




@Database(entities = {User.class, Bin.class},version = 1)
public abstract class Appdatabase extends RoomDatabase {

    public abstract UserDao getuserDao();
    public abstract BinDao getbinDao();



    private static Appdatabase StDatabase;


    // method user for get input from the instance

    public static Appdatabase getdbInstance(Context context)
    {
        if(StDatabase==null)
        {
            StDatabase= Room.databaseBuilder(context.getApplicationContext(), Appdatabase.class,"user Data info")
                    .allowMainThreadQueries()
                    .build();

        }
        return StDatabase;
    }
    public void cleanUp(){
        StDatabase = null;
    }

}
