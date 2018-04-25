package Database;

import java.util.List;

import POJO.User;

/**
 * Created by vaks on 06/04/2018.
 */

public interface StorageManager {

    void addNewUser(User user);
    void updateUser(User user);
    User getUserByEmail(String email);
    boolean validateLogin(String email, String password);
    public User getCurrentUser();
}
