package com.gka.gkamobile.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.gka.gkamobile.dao.UserDao;
import com.gka.gkamobile.models.User;

@Database(entities = User.class, exportSchema = false, version = 1)
public abstract class GkaDatabase extends RoomDatabase {
     private static final String DB_NAME = "gka.db";
     private static GkaDatabase instance;

     public static synchronized GkaDatabase getInstance(Context context){
          if(instance == null ){
               instance = Room.databaseBuilder(context.getApplicationContext(), GkaDatabase.class, DB_NAME)
                       .fallbackToDestructiveMigration()
                       .allowMainThreadQueries()
                       .build();
          }

          return instance;
     }

     public abstract UserDao userDao();

}


