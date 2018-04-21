package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import POJO.StoredUser;
import POJO.User;

/**
 * Created by sergio on 25/03/2018.
 */

@Dao
public interface UserDAO {

    @Query("SELECT * FROM Users")
    List<StoredUser> getUsers();

    @Insert
    void addUser(User user);

    @Update
    void updateUser(User user);

    @Query ("SELECT password FROM Users WHERE email = :email ")
    String getPasswordByEmail(String email);

    @Query ("SELECT * FROM Users WHERE email = :email ")
    StoredUser getUserByEmail(String email);

    // Delete all
    @Query("DELETE FROM Users")
    void clearUsers();

    @Delete
    void deleteUser(User user);
}