package Database;

import java.util.List;

import POJO.User;

/**
 * Created by vaks on 06/04/2018.
 */

public interface StorageManager {

    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    User getUserByEmail(String email);
    Boolean validateLogin(String email);
    List<User> getUsers();

    void addCategory(User user);
    void deleteCategory(User user);
    void updateCategory(User user);
    List<User> getCategories();



}
