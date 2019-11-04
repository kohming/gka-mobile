package com.gka.gkamobile.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.gka.gkamobile.models.User;
import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> getUserList();

    @Insert
    void insertUser(User user);

    @Update
    void  updateUser(User user);

    @Delete
    void deteleUser(User user);

}
